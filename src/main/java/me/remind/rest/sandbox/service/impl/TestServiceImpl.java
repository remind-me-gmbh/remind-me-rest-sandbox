package me.remind.rest.sandbox.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.ServiceResponseDTO;
import me.remind.rest.sandbox.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
/**
 * A simple implementation of a service using a spring restTemplate.
 */
public class TestServiceImpl implements TestService {

    @Value("${test.service.url}")
    private String testServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public TestServiceImpl() {
    }

    public TestServiceImpl(String testServiceUrl) {
        this.testServiceUrl = testServiceUrl;
    }

    @Override
    /**
     * Access the external rest service and return a result.
     */
    public ServiceResponseDTO getTestServiceResult() {

        try {
            ResponseEntity<ServiceResponseDTO> response = restTemplate.getForEntity(testServiceUrl, ServiceResponseDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("Service endpoint not found " + testServiceUrl, e);
            }
            throw new RuntimeException("Failed to GET url " + testServiceUrl, e);
        }
    }

}
