package com.musala.gateways.api.exception;

import com.musala.gateways.api.utils.ValidationConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceNotFoundException extends Exception{
    private final ValidationConst validationValue;

    public DeviceNotFoundException(ValidationConst validationValue, String message) {
        super(message);
        this.validationValue = validationValue;
    }
}
