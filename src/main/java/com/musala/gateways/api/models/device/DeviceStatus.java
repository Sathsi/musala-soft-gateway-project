package com.musala.gateways.api.models.device;

public enum DeviceStatus {
    ONLINE("online"),
    OFFLINE("offline");

    private String value;

    DeviceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
