package com.musala.gateways.api.persistence.repository;

import com.musala.gateways.api.persistence.dto.device.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Device findDeviceByUid(int deviceId);
}
