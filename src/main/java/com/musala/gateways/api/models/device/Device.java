package com.musala.gateways.api.models.device;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Device {

    private int uid;
    private String vendor;
    private Date createdDate;
    private String status;
    private String gatewaySerialNumber;


}
