package com.musala.gateways.api.controller;

import com.musala.gateways.api.exception.DeviceNotFoundException;
import com.musala.gateways.api.exception.DeviceValidationException;
import com.musala.gateways.api.exception.GatewayNotFoundException;
import com.musala.gateways.api.exception.GatewayValidationException;
import com.musala.gateways.api.service.impl.GatewayServiceImpl;
import com.musala.gateways.api.utils.ErrorInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapperHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapperHandler.class);
    private static final String GATEWAY_EXCEPTION = "Gateway Exception : ";
    private static final String DEVICE_EXCEPTION = "Device Exception : ";

    @ExceptionHandler(GatewayValidationException.class)
    public ResponseEntity<ErrorInformation> gatewayValidationFailure(final GatewayValidationException ex) {
        LOGGER.error(GATEWAY_EXCEPTION + ex.getMessage());
        final ErrorInformation errorInfo = new ErrorInformation(ex.getValidationValue().code(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GatewayNotFoundException.class)
    public ResponseEntity<ErrorInformation> gatewayNotFound(final GatewayNotFoundException ex) {
        LOGGER.error(GATEWAY_EXCEPTION + ex.getMessage());
        final ErrorInformation errorInfo = new ErrorInformation(ex.getValidationValue().code(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceValidationException.class)
    public ResponseEntity<ErrorInformation> deviceValidationFailure(final DeviceValidationException ex) {
        LOGGER.error(DEVICE_EXCEPTION + ex.getMessage());
        final ErrorInformation errorInfo = new ErrorInformation(ex.getValidationValue().code(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<ErrorInformation> deviceNotFound(final DeviceNotFoundException ex) {
        LOGGER.error(DEVICE_EXCEPTION + ex.getMessage());
        final ErrorInformation errorInfo = new ErrorInformation(ex.getValidationValue().code(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
