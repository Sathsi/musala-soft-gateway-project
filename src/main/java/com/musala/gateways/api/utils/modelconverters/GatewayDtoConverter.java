package com.musala.gateways.api.utils.modelconverters;

import com.musala.gateways.api.models.device.Device;
import com.musala.gateways.api.models.gateway.Gateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GatewayDtoConverter {

   public Gateway dtoToGatewayResponse (final com.musala.gateways.api.persistence.dto.gateway.Gateway repoGateway) {
       final Gateway responseGateway = new Gateway();
       if(repoGateway != null) {
           responseGateway.setId(repoGateway.getId());
           responseGateway.setSerialNumber(repoGateway.getSerialNumber());
           responseGateway.setName(repoGateway.getName());
           responseGateway.setIpv4Address(repoGateway.getIpv4_Address());
           if(repoGateway.getDevices() != null) {
               responseGateway.setDevices(deviceDtoListToDeviceResponseList(repoGateway.getDevices()));
           }else
               responseGateway.setDevices(new ArrayList<>());
       }
       return responseGateway;
   }

   private List<Device> deviceDtoListToDeviceResponseList(final List<com.musala.gateways.api.persistence.dto.device.Device> dbDeviceList) {
       final List<Device> devices =  new ArrayList<>();
       for (final com.musala.gateways.api.persistence.dto.device.Device dbDevice: dbDeviceList) {
           final Device modelDevice = new Device();
           modelDevice.setUid(dbDevice.getUid());
           modelDevice.setCreatedDate(dbDevice.getCreatedDate());
           modelDevice.setStatus(dbDevice.getStatus());
           modelDevice.setVendor(dbDevice.getVendor());
           modelDevice.setGatewaySerialNumber(dbDevice.getGateway().getSerialNumber());
           devices.add(modelDevice);
       }
       return devices;
   }

   public com.musala.gateways.api.persistence.dto.gateway.Gateway gatewayCreateRequestToDto (final Gateway gatewayRequest) {
       final com.musala.gateways.api.persistence.dto.gateway.Gateway dbGateway = new com.musala.gateways.api.persistence.dto.gateway.Gateway();
       if (gatewayRequest != null) {
           dbGateway.setName(gatewayRequest.getName());
           dbGateway.setSerialNumber(gatewayRequest.getSerialNumber());
           dbGateway.setIpv4_Address(gatewayRequest.getIpv4Address());
       }
       return dbGateway;
   }



}
