package com.musala.gateways.api.service;

import com.musala.gateways.api.Application;
import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.persistence.dto.device.Device;
import com.musala.gateways.api.persistence.repository.DeviceRepository;
import com.musala.gateways.api.service.impl.DeviceServiceImpl;
import com.musala.gateways.api.utils.RequestValidator;
import com.musala.gateways.api.utils.ValidationConst;
import com.musala.gateways.api.utils.modelconverters.DeviceDtoConverter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, DeviceService.class})
@TestPropertySource(locations= "classpath:test.properties")
@ActiveProfiles("test")
public class DeviceServiceTest {

    private static final int DEVICE_1_UID = 22340;
    private static final int DEVICE_2_UID = 22345;
    private static final String DEVICE_1_VENDOR = "testVendor1Name";
    private static final String DEVICE_2_VENDOR = "testVendor2Name";
    private static final String DEVICE_1_STATUS = "online";
    private static final String DEVICE_2_STATUS = "invalidOffline";
    private static final String DEVICE_1_GATEWAY_SERIAL_NUMBER = "MV1921VS004Q";
    private static final String DEVICE_2_GATEWAY_SERIAL_NUMBER = "11400678234";

    private static final int GATEWAY_1_ID = 634789;
    private static final String GATEWAY_1_SERIAL_NUMBER = "MV1921VS004Q";
    private static final String GATEWAY_1_NAME = "ARRIS NVG448";
    private static final String GATEWAY_1_IPV4_ADDRESS = "172.16.254.2";


    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @InjectMocks
    @Spy
    RequestValidator requestValidatorOrigin;
    @Autowired
    private DeviceService deviceService;
    @InjectMocks
    private DeviceServiceImpl deviceServiceImpl;
    @Mock
    private GatewayService gatewayService;
    @Mock
    private DeviceDtoConverter deviceDtoConverter;
    @Mock
    private RequestValidator requestValidator;
    @Mock
    private DeviceRepository deviceRepository;

    private Device testDBDevice1;
    private com.musala.gateways.api.models.device.Device testModelDevice1;
    private com.musala.gateways.api.models.device.Device testModelDevice2;
    private com.musala.gateways.api.persistence.dto.gateway.Gateway testDBGateway1;
    private Gateway testModelGateway1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private void initVariables() throws Exception {

        testDBGateway1 = new com.musala.gateways.api.persistence.dto.gateway.Gateway();
        testDBGateway1.setId(GATEWAY_1_ID);
        testDBGateway1.setSerialNumber(GATEWAY_1_SERIAL_NUMBER);
        testDBGateway1.setName(GATEWAY_1_NAME);
        testDBGateway1.setIpv4_Address(GATEWAY_1_IPV4_ADDRESS);

        testModelGateway1 = new Gateway();
        testModelGateway1.setId(GATEWAY_1_ID);
        testModelGateway1.setSerialNumber(GATEWAY_1_SERIAL_NUMBER);
        testModelGateway1.setName(GATEWAY_1_NAME);
        testModelGateway1.setIpv4Address(GATEWAY_1_IPV4_ADDRESS);

        testDBDevice1 = new Device();
        testDBDevice1.setUid(DEVICE_1_UID);
        testDBDevice1.setVendor(DEVICE_1_VENDOR);
        testDBDevice1.setStatus(DEVICE_1_STATUS);
        testDBDevice1.setGateway(testDBGateway1);

    }

    @Test
    public void testWhenSave_thenDeviceShouldBeFound() throws Exception {
        initVariables();
        Mockito.when(deviceDtoConverter.deviceCreateRequestToDto(testModelDevice1, testDBGateway1)).thenReturn(testDBDevice1);
        Mockito.when(deviceRepository.save(testDBDevice1)).thenReturn(testDBDevice1);
        Mockito.when(deviceDtoConverter.dtoToDeviceResponse(testDBDevice1)).thenReturn(testModelDevice1);
        Mockito.when(gatewayService.getGateway(GATEWAY_1_SERIAL_NUMBER)).thenReturn(testModelGateway1);

        com.musala.gateways.api.models.device.Device savedDevice = deviceServiceImpl.createDevice(testModelDevice1);

        assertNotNull(savedDevice);
        assertThat(testModelDevice1.getUid()).isEqualTo(savedDevice.getUid());
    }

    @Test
    public void ShouldThrownExceptionWhenCreateDeviceWithInvalidStatus() throws Exception{
        testModelDevice2 = new com.musala.gateways.api.models.device.Device();
        testModelDevice2.setUid(DEVICE_2_UID);
        testModelDevice2.setVendor(DEVICE_2_VENDOR);
        testModelDevice2.setStatus(DEVICE_2_STATUS);
        testModelDevice2.setGatewaySerialNumber(DEVICE_2_GATEWAY_SERIAL_NUMBER);

        exceptionRule.expectMessage(ValidationConst.INVALID_DEVICE_STATUS.message());
        requestValidatorOrigin.validateDeviceCreateRequest(testModelDevice2);

    }

    @Test
    public void ShouldThrownExceptionWhenCreateDeviceWithWrongGatewaySerialNumber() throws Exception{
        Mockito.when(gatewayService.getGateway(GATEWAY_1_SERIAL_NUMBER)).thenReturn(testModelGateway1);
        testModelDevice2 = new com.musala.gateways.api.models.device.Device();
        testModelDevice2.setUid(DEVICE_2_UID);
        testModelDevice2.setVendor(DEVICE_2_VENDOR);
        testModelDevice2.setStatus(DEVICE_1_STATUS);
        testModelDevice2.setGatewaySerialNumber(GATEWAY_1_SERIAL_NUMBER+"invalid");
        exceptionRule.expectMessage(ValidationConst.GATEWAY_NOT_FOUND.message());
        requestValidatorOrigin.validateDeviceCreateRequest(testModelDevice2);
    }

    @Test
    public void testDeleteDeviceByUid() throws Exception {
        Mockito.when(deviceRepository.save(testDBDevice1)).thenReturn(testDBDevice1);
        deviceServiceImpl.deleteDevice(DEVICE_1_UID);
        Mockito.verify(deviceRepository, Mockito.times(1)).delete(testDBDevice1);
    }

}