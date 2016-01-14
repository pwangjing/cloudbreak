package com.sequenceiq.cloudbreak.service.topology;


import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.common.type.APIResourceType;
import com.sequenceiq.cloudbreak.controller.NotFoundException;
import com.sequenceiq.cloudbreak.domain.CbUser;
import com.sequenceiq.cloudbreak.domain.Topology;
import com.sequenceiq.cloudbreak.repository.TopologyRepository;
import com.sequenceiq.cloudbreak.service.DuplicateKeyValueException;

@Component
public class SimpleTopologyService implements TopologyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTopologyService.class);
    private static final String DELIMITER = "_";
    private static final String TOPOLOGY_NOT_FOUND_MSG = "Topology '%s' not found.";

    @Inject
    private TopologyRepository topologyRepository;

    @Override
    public Topology create(CbUser user, Topology topology) {
        LOGGER.debug("Creating topology: [User: '{}', Account: '{}']", user.getUsername(), user.getAccount());
        Topology savedTopology = null;
        topology.setOwner(user.getUserId());
        topology.setAccount(user.getAccount());
        try {
            savedTopology = topologyRepository.save(topology);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateKeyValueException(APIResourceType.TOPOLOGY, topology.getName(), ex);
        }
        return savedTopology;
    }

    @Override
    public void delete(Long topologyId, CbUser user) {
        Topology topology = topologyRepository.findByIdInAccount(topologyId, user.getAccount());
        if (topology == null) {
            throw new NotFoundException(String.format(TOPOLOGY_NOT_FOUND_MSG, topologyId));
        }
        delete(topology, user);
    }

    private void delete(Topology topology, CbUser user) {
        LOGGER.debug("Deleting topology. {} - {}", new Object[]{topology.getId(), topology.getName()});
        Date now = new Date();
        String terminatedName = topology.getName() + DELIMITER + now.getTime();
        topology.setName(terminatedName);
        topology.setDeleted(true);
        topologyRepository.save(topology);
    }
}