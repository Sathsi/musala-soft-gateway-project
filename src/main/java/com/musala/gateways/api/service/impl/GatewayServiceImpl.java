package com.musala.gateways.api.service.impl;

import com.musala.gateways.api.exception.GatewayNotFoundException;
import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.persistence.repository.GatewayRepository;
import com.musala.gateways.api.service.GatewayService;
import com.musala.gateways.api.utils.RequestValidator;
import com.musala.gateways.api.utils.ValidationConst;
import com.musala.gateways.api.utils.modelconverters.GatewayDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GatewayServiceImpl implements GatewayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServiceImpl.class);

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private GatewayDtoConverter gatewayDtoConverter;

    @Autowired
    private RequestValidator requestValidator;

    @Override
    public List<Gateway> getAllGateways() throws Exception {
        LOGGER.info("Get all gateways service invoked");
        final List<Gateway> resultList = new ArrayList<>();

        final Iterable<com.musala.gateways.api.persistence.dto.gateway.Gateway> dbGatewayList =
                gatewayRepository.findAll();

        for(com.musala.gateways.api.persistence.dto.gateway.Gateway dbGateway : dbGatewayList) {
            final Gateway gateway = gatewayDtoConverter.dtoToGatewayResponse(dbGateway);
            resultList.add(gateway);
        }
        return resultList;
    }

    @Override
    public Gateway createGateway(Gateway gateway) throws Exception {
        LOGGER.info("Create gateway service invoked");
        requestValidator.validateGatewayCreateRequest(gateway);

        final com.musala.gateways.api.persistence.dto.gateway.Gateway savedGateway =
                gatewayRepository.save(gatewayDtoConverter.gatewayCreateRequestToDto(gateway));

        return gatewayDtoConverter.dtoToGatewayResponse(savedGateway);

    }

    @Override
    public Gateway getGateway(String serialNumber) throws Exception {
        LOGGER.info("Get gateway by serial number service invoked : " + serialNumber);
        final com.musala.gateways.api.persistence.dto.gateway.Gateway dbGateway = gatewayRepository.findGatewayBySerialNumber(serialNumber);
        if (dbGateway != null) {
            final Gateway gatewayResponse = gatewayDtoConverter.dtoToGatewayResponse(dbGateway);
            return gatewayResponse;
        }else {
            throw new GatewayNotFoundException(ValidationConst.GATEWAY_NOT_FOUND, ValidationConst.GATEWAY_NOT_FOUND.message());
        }
    }

    @Override
    public com.musala.gateways.api.persistence.dto.gateway.Gateway getRepoGateway(String serialNumber) throws Exception {
        return gatewayRepository.findGatewayBySerialNumber(serialNumber);
    }
}
