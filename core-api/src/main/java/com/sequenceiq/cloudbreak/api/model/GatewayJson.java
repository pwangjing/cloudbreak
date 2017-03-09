package com.sequenceiq.cloudbreak.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class GatewayJson {

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.ENABLE_KNOX_GATEWAY)
    private Boolean enableGateway;

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.KNOX_PATH)
    private String path;

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.KNOX_TOPOLOGY_NAME)
    private String topologyName;

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.EXPOSED_KNOX_SERVICES)
    private List<String> exposedServices;

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.KNOX_SSO_PROVIDER)
    private String ssoProvider;

    @ApiModelProperty(value = ModelDescriptions.GatewayModelDescription.KNOX_SSO_CERT)
    private String signKey;

    public Boolean getEnableGateway() {
        return enableGateway;
    }

    public void setEnableGateway(Boolean enableGateway) {
        this.enableGateway = enableGateway;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTopologyName() {
        return topologyName;
    }

    public void setTopologyName(String topologyName) {
        this.topologyName = topologyName;
    }

    public List<String> getExposedServices() {
        return exposedServices;
    }

    public void setExposedServices(List<String> exposedServices) {
        this.exposedServices = exposedServices;
    }

    public String getSsoProvider() {
        return ssoProvider;
    }

    public void setSsoProvider(String ssoProvider) {
        this.ssoProvider = ssoProvider;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

}
