package com.musala.gateways.api.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInformation {
    private final String code;
    private final String message;

    public ErrorInformation(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
