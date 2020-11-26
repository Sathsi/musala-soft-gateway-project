package com.musala.gateways.api.exception;

import com.musala.gateways.api.utils.ValidationConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewayValidationException extends Exception {

    private ValidationConst validationValue;

    public GatewayValidationException(ValidationConst validationValue, final String message) {
        super(message);
        this.validationValue = validationValue;
    }
}
