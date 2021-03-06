package com.sequenceiq.cloudbreak.converter;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.model.AutoscaleStackResponse;
import com.sequenceiq.cloudbreak.domain.Cluster;
import com.sequenceiq.cloudbreak.domain.Stack;
import com.sequenceiq.cloudbreak.service.GatewayConfigService;

@Component
public class StackToAutoscaleStackResponseJsonConverter extends AbstractConversionServiceAwareConverter<Stack, AutoscaleStackResponse> {

    @Inject
    private GatewayConfigService gatewayConfigService;

    @Override
    public AutoscaleStackResponse convert(Stack source) {
        AutoscaleStackResponse result = new AutoscaleStackResponse();
        result.setStackId(source.getId());
        result.setName(source.getName());
        result.setOwner(source.getOwner());
        result.setAccount(source.getOwner());
        result.setGatewayPort(source.getGatewayPort());
        result.setCreated(source.getCreated());
        result.setStatus(source.getStatus());

        if (source.getCluster() != null) {
            Cluster cluster = source.getCluster();
            String gatewayIp = gatewayConfigService.getGatewayIp(source);
            result.setAmbariServerIp(gatewayIp);
            result.setUserName(cluster.getUserName());
            result.setPassword(cluster.getPassword());
        }
        return result;
    }
}
