package com.musala.gateways.api.utils;

public enum ValidationConst {

    GATEWAY_NOT_FOUND("Gateway not found", "ER1000"),
    GATEWAY_SERIAL_NUMBER_EXISTS_ERROR("Gateway serial number already exists", "ER1001"),
    GATEWAY_IPV4_ADDRESS_IS_INVALID("Gateway IPv4 address is invalid", "ER1002"),
    INVALID_DEVICE_STATUS("Device status is invalid", "ER1003"),
    DEVICE_NOT_FOUND("Device not found", "ER1004"),
    MAXIMUM_DEVICES_FOR_GATEWAY_REACHED("This gateway already has maximum number of devices.","ER1005"),

    GATEWAY_SERIAL_NUMBER_VALID("Gateway serial number is valid.", "INF3000");

    private final String msg;
    private final String code;

    ValidationConst(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String message() {
        return msg;
    }

    public String code() {
        return code;
    }
}
