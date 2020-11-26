package com.musala.gateways.api.service;

import com.musala.gateways.api.Application;
import com.musala.gateways.api.models.gateway.Gateway;
import com.musala.gateways.api.persistence.repository.GatewayRepository;
import com.musala.gateways.api.service.impl.GatewayServiceImpl;
import com.musala.gateways.api.utils.RequestValidator;
import com.musala.gateways.api.utils.ValidationConst;
import com.musala.gateways.api.utils.modelconverters.GatewayDtoConverter;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, GatewayService.class})
@TestPropertySource(locations= "classpath:test.properties")
@ActiveProfiles("test")
public class GatewayServiceTest {

    private static final int GATEWAY_1_ID = 634789;
    private static final String GATEWAY_1_SERIAL_NUMBER = "MV1921VS004Q";
    private static final String GATEWAY_1_NAME = "ARRIS NVG448";
    private static final String GATEWAY_1_IPV4_ADDRESS = "172.16.254.2";

    private static final int GATEWAY_2_ID = 634789;
    private static final String GATEWAY_2_SERIAL_NUMBER = "MV19789S004Q";
    private static final String GATEWAY_2_NAME = "ARRIS TS567";
    private static final String GATEWAY_2_IPV4_ADDRESS = "172.56.254.3";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @InjectMocks
    @Spy
    RequestValidator requestValidatorOrigin;
    @Autowired
    GatewayService gatewayService;
    @InjectMocks
    GatewayServiceImpl gatewayServiceImpl;
    @Mock
    private GatewayRepository gatewayRepository;
    @Mock
    private GatewayDtoConverter gatewayDtoConverter;
    @Mock
    private RequestValidator requestValidator;

    private com.musala.gateways.api.persistence.dto.gateway.Gateway testDBGateway1;
    private Gateway testModelGateway1;
    private com.musala.gateways.api.persistence.dto.gateway.Gateway testDBGateway2;
    private Gateway testModelGateway2;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private void initVariables() throws Exception {
        testDBGateway1 = new com.musala.gateways.api.persistence.dto.gateway.Gateway();
        testDBGateway1.setId(GATEWAY_1_ID);
        testDBGateway1.setName(GATEWAY_1_NAME);
        testDBGateway1.setSerialNumber(GATEWAY_1_SERIAL_NUMBER);
        testDBGateway1.setIpv4_Address(GATEWAY_1_IPV4_ADDRESS);

        testModelGateway1 = new Gateway();
        testModelGateway1.setId(GATEWAY_1_ID);
        testModelGateway1.setName(GATEWAY_1_NAME);
        testModelGateway1.setSerialNumber(GATEWAY_1_SERIAL_NUMBER);
        testModelGateway1.setIpv4Address(GATEWAY_1_IPV4_ADDRESS);

    }

    @Test
    public void testWhenSave_thenGatewayShouldBeFound() throws Exception {
        initVariables();

        Mockito.when(gatewayDtoConverter.gatewayCreateRequestToDto(testModelGateway1)).thenReturn(testDBGateway1);
        Mockito.when(gatewayRepository.save(testDBGateway1)).thenReturn(testDBGateway1);
        Mockito.when(gatewayDtoConverter.dtoToGatewayResponse(testDBGateway1)).thenReturn(testModelGateway1);

       Gateway savedGateway = gatewayService.createGateway(testModelGateway1);

        assertNotNull(savedGateway);
        assertThat(testModelGateway1.getId()).isEqualTo(savedGateway.getId());
    }

    @Test
    public void testWhenFIndAll_thenGatewayListShouldBeReturned() throws Exception{
        initVariables();
        Mockito.when(gatewayDtoConverter.dtoToGatewayResponse(testDBGateway1)).thenReturn(testModelGateway1);

        List<Gateway> foundGatewayList = gatewayService.getAllGateways();
        assertNotNull(foundGatewayList);
        assertEquals(1, foundGatewayList.size());
    }

    @Test
    public void testWhenValidSerialNumberGiven_thenGatewayShouldBeFound() throws Exception{
        initVariables();
        Mockito.when(gatewayDtoConverter.dtoToGatewayResponse(testDBGateway1)).thenReturn(testModelGateway1);
        Gateway foundGateway = gatewayService.getGateway(GATEWAY_1_SERIAL_NUMBER);
        assertNotNull(foundGateway);
        assertThat(testModelGateway1.getName()).isEqualTo(foundGateway.getName());
        assertThat(testModelGateway1.getSerialNumber()).isEqualTo(foundGateway.getSerialNumber());

    }

    @Test
    public void ShouldThrownExceptionWhenCreateDeviceWithExistingSerialNumber() throws Exception {
        initVariables();
        testModelGateway2 = new Gateway();
        testModelGateway2.setId(GATEWAY_2_ID);
        testModelGateway2.setName(GATEWAY_2_NAME);
        testModelGateway2.setSerialNumber(GATEWAY_1_SERIAL_NUMBER);
        testModelGateway2.setIpv4Address(GATEWAY_2_IPV4_ADDRESS);

        Mockito.when(gatewayDtoConverter.dtoToGatewayResponse(testDBGateway1)).thenReturn(testModelGateway1);
        exceptionRule.expectMessage(ValidationConst.GATEWAY_SERIAL_NUMBER_EXISTS_ERROR.message());
        requestValidatorOrigin.validateGatewayCreateRequest(testModelGateway2);

    }

    @Test
    public void ShouldThrownExceptionWhenCreateDeviceWithInvalidIPv4Address() throws Exception {
        initVariables();
        testModelGateway2 = new Gateway();
        testModelGateway2.setId(GATEWAY_2_ID);
        testModelGateway2.setName(GATEWAY_2_NAME);
        testModelGateway2.setSerialNumber(GATEWAY_2_SERIAL_NUMBER);
        testModelGateway2.setIpv4Address("000.12.234.23.23");

        Mockito.when(gatewayDtoConverter.dtoToGatewayResponse(testDBGateway1)).thenReturn(testModelGateway1);
        exceptionRule.expectMessage(ValidationConst.GATEWAY_IPV4_ADDRESS_IS_INVALID.message());
        requestValidatorOrigin.validateGatewayCreateRequest(testModelGateway2);

    }

}