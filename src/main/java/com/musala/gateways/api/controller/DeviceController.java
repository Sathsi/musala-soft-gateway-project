package com.musala.gateways.api.controller;

import com.musala.gateways.api.models.device.Device;
import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("/peripheralDevices")
@Api(description = "CRUD operations for the device", tags = "Peripheral Device")
public class DeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "Creates a Device", notes = "Enter necessary details to the given attributes to create a Device.")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully created the device", response = Gateway.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid | Empty Input Parameters in the Request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to create a device"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Creating the particular device is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while creating the device")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createDevice(@Valid final @RequestBody Device device) throws Exception {
        LOGGER.info("Create device API invoked");
        final Device deviceResponse = deviceService.createDevice(device);
        return new ResponseEntity<Object>(deviceResponse, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Delete a device", notes = "Enter the device uid of a particular device you want to remove from gateway")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully deleted the device", response = Gateway.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway you were trying to delete is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to delete the device"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Deleting the particular device is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while deleting the device")
    })
    @RequestMapping(value = "/{deviceId}" , method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDevice(@Valid final @PathVariable("deviceId") int deviceId) throws Exception {
        LOGGER.info("Delete device API invoked");
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
