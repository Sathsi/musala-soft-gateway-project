package com.musala.gateways.api.exception;

import com.musala.gateways.api.utils.ValidationConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceValidationException extends Exception {

    private final ValidationConst validationValue;

    public DeviceValidationException(ValidationConst validationValue, final String message) {
        super(message);
        this.validationValue = validationValue;
    }
}
