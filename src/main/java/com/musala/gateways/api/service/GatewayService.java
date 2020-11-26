package com.musala.gateways.api.service;

import com.musala.gateways.api.models.gateway.Gateway;

import java.util.List;

public interface GatewayService {

    List<Gateway> getAllGateways() throws Exception;

    Gateway createGateway(Gateway gateway) throws Exception;

    Gateway getGateway(String serialNumber) throws Exception;

    com.musala.gateways.api.persistence.dto.gateway.Gateway getRepoGateway (String serialNumber) throws Exception;
}
