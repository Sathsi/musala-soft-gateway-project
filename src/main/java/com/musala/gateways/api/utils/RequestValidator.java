package com.musala.gateways.api.utils;

import com.musala.gateways.api.exception.DeviceValidationException;
import com.musala.gateways.api.exception.GatewayNotFoundException;
import com.musala.gateways.api.exception.GatewayValidationException;
import com.musala.gateways.api.models.device.Device;
import com.musala.gateways.api.models.device.DeviceStatus;
import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.service.GatewayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

    @Autowired
    private GatewayService gatewayService;

    @Value("${device.max-device-for-gateway}")
    private int maxDeviceCount;

    public void validateGatewayCreateRequest(final Gateway gateway) throws Exception{

        try{
            if(gatewayService.getGateway(gateway.getSerialNumber()) != null) {
                throw new GatewayValidationException(ValidationConst.GATEWAY_SERIAL_NUMBER_EXISTS_ERROR, ValidationConst.GATEWAY_SERIAL_NUMBER_EXISTS_ERROR.message());
            }
        } catch (GatewayNotFoundException ex) {
            LOGGER.trace(ValidationConst.GATEWAY_SERIAL_NUMBER_VALID.message());
        }

        if(!isValidIPv4Address(gateway.getIpv4Address())) {
            throw new GatewayValidationException(ValidationConst.GATEWAY_IPV4_ADDRESS_IS_INVALID, ValidationConst.GATEWAY_IPV4_ADDRESS_IS_INVALID.message());
        }

    }

    private static boolean isValidIPv4Address(String ipv4Address) {
        String zeroTo255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

        Pattern p = Pattern.compile(regex);

        if (ipv4Address == null) {
            return false;
        }
        Matcher m = p.matcher(ipv4Address);
        return m.matches();
    }

    public void validateDeviceCreateRequest(final Device device) throws Exception{
        final Gateway gateway = gatewayService.getGateway(device.getGatewaySerialNumber());
        if (gateway.getDevices().size() >= maxDeviceCount) {
            throw new DeviceValidationException(ValidationConst.MAXIMUM_DEVICES_FOR_GATEWAY_REACHED, ValidationConst.MAXIMUM_DEVICES_FOR_GATEWAY_REACHED.message());
        }

        if(!(DeviceStatus.ONLINE.getValue().equalsIgnoreCase(device.getStatus())
                || DeviceStatus.OFFLINE.getValue().equalsIgnoreCase(device.getStatus()))) {
            throw new DeviceValidationException(ValidationConst.INVALID_DEVICE_STATUS, ValidationConst.INVALID_DEVICE_STATUS.message());
        }
    }
}
