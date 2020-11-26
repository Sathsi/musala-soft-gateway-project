package com.musala.gateways.api.service;

import com.musala.gateways.api.models.device.Device;

public interface DeviceService {

    Device createDevice (Device device) throws Exception;

    void deleteDevice(int deviceId) throws Exception;

}
