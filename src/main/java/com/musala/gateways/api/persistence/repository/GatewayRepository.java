package com.musala.gateways.api.persistence.repository;

import com.musala.gateways.api.persistence.dto.gateway.Gateway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends CrudRepository<Gateway, Long> {

    Gateway findGatewayBySerialNumber(String serialNumber);
}
