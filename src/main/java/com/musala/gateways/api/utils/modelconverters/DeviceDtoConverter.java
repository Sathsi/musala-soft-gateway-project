package com.musala.gateways.api.utils.modelconverters;

import com.musala.gateways.api.models.device.Device;
import com.musala.gateways.api.models.gateway.Gateway;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeviceDtoConverter {

    public com.musala.gateways.api.persistence.dto.device.Device deviceCreateRequestToDto (final Device DeviceRequest, final com.musala.gateways.api.persistence.dto.gateway.Gateway gateway) {
        final com.musala.gateways.api.persistence.dto.device.Device dbDevice = new com.musala.gateways.api.persistence.dto.device.Device();
        if (DeviceRequest != null) {
            dbDevice.setVendor(DeviceRequest.getVendor());
            dbDevice.setCreatedDate(new Date());
            dbDevice.setStatus(DeviceRequest.getStatus().toLowerCase());
            dbDevice.setGateway(gateway);
        }
        return dbDevice;
    }

    public Device dtoToDeviceResponse (final com.musala.gateways.api.persistence.dto.device.Device repoDevice) {
        final Device responseDevice = new Device();
        if(repoDevice != null) {
            responseDevice.setUid(repoDevice.getUid());
            responseDevice.setVendor(repoDevice.getVendor());
            responseDevice.setCreatedDate(repoDevice.getCreatedDate());
            responseDevice.setStatus(repoDevice.getStatus());
            responseDevice.setGatewaySerialNumber(repoDevice.getGateway().getSerialNumber());
        }
        return responseDevice;
    }
}
