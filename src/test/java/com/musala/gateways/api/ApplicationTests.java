package com.musala.gateways.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations= "classpath:test.properties")
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
