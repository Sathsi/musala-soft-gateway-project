package com.musala.gateways.api.controller;

import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.service.GatewayService;
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
import java.util.List;


@RestController
@RequestMapping("/gateways")
@Api(description = "CRUD operations for the gateway", tags = "Gateway")
public class GatewayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    private GatewayService gatewayService;

    @ApiOperation(value = "Creates a Gateway", notes = "Enter necessary details to the given attributes to create a Gateway.")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully created the gateway", response = Gateway.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid | Empty Input Parameters in the Request"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to create a gateway"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Creating the particular gateway is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while creating the gateway")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createGateway(@Valid final @RequestBody Gateway gateway) throws Exception {
        LOGGER.info("Create gateway API invoked");
        final Gateway gatewayResponse = gatewayService.createGateway(gateway);
        return new ResponseEntity<Object>(gatewayResponse, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get all Gateways", notes = "Will retrieve a list of all the created gateways")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully retrieve the gateway list", response = Gateway.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway list you were trying to reach is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to view the gateway list"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accessing the gateway list you were trying to reach is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while retrieving the gateway list")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAllGateways() throws Exception {
        LOGGER.info("Get all gateways API invoked");
        final List<Gateway> gatewayList= gatewayService.getAllGateways();
        return new ResponseEntity<Object>(gatewayList, HttpStatus.OK);
    }


    @ApiOperation(value = "Get a single Gateways", notes = "Enter the serial number of a particular gateway you want to retrieve")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully retrieve the gateway", response = Gateway.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway you were trying to reach is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to view the gateway"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accessing the gateway you were trying to reach is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while retrieving the gateway")
    })
    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.GET)
    public ResponseEntity<Object> getGatewayBySerialNumber(@Valid final @PathVariable("serialNumber") String serialNumber) throws Exception{
        LOGGER.info("Get gateway API invoked");
        final Gateway gatewayResponse = gatewayService.getGateway(serialNumber);
        return new ResponseEntity<Object>(gatewayResponse, HttpStatus.OK);
    }

}
