package com.musala.gateways.api.service.impl;

import com.musala.gateways.api.exception.DeviceNotFoundException;
import com.musala.gateways.api.models.device.Device;
import com.musala.gateways.api.persistence.dto.gateway.Gateway;
import com.musala.gateways.api.persistence.repository.DeviceRepository;
import com.musala.gateways.api.service.DeviceService;
import com.musala.gateways.api.service.GatewayService;
import com.musala.gateways.api.utils.RequestValidator;
import com.musala.gateways.api.utils.ValidationConst;
import com.musala.gateways.api.utils.modelconverters.DeviceDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private DeviceDtoConverter deviceDtoConverter;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private GatewayService gatewayService;

    @Override
    public Device createDevice(Device device) throws Exception {
        LOGGER.info("Create device service invoked");
        requestValidator.validateDeviceCreateRequest(device);

        final Gateway existGateway = gatewayService.getRepoGateway(device.getGatewaySerialNumber());
        final com.musala.gateways.api.persistence.dto.device.Device savedDevice =
                deviceRepository.save(deviceDtoConverter.deviceCreateRequestToDto(device, existGateway));

        return deviceDtoConverter.dtoToDeviceResponse(savedDevice);
    }

    @Override
    public void deleteDevice(int deviceId) throws Exception {
        LOGGER.info("Delete device service invoked : " + deviceId);
        deviceRepository.delete(deviceRepository.findDeviceByUid(deviceId));
    }

}
