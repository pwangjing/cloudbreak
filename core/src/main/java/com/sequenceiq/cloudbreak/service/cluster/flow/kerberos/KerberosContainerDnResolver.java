package com.sequenceiq.cloudbreak.service.cluster.flow.kerberos;

import org.springframework.stereotype.Service;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.sequenceiq.cloudbreak.domain.KerberosConfig;

@Service
public class KerberosContainerDnResolver {

    public String resolveContainerDnForKerberos(KerberosConfig kerberosConfig) {
        return Strings.isNullOrEmpty(kerberosConfig.getKerberosContainerDn()) ? null : kerberosConfig.getKerberosContainerDn();
    }
}
