package com.musala.gateways.api.models.gateway;

import com.musala.gateways.api.models.device.Device;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Gateway {

    private int id;
    @NotNull(message = "Gateway Serial Number cannot be null")
    private String serialNumber;
    @NotNull(message = "Gateway name cannot be null")
    private String name;

    @NotNull(message = "Gateway IPV4 address cannot be null")
    @Column(name="ipv4_Address")
    private String ipv4Address;

    @ApiModelProperty(hidden=true)
    private List<Device> devices;
}
