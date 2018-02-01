package me.remind.rest.sandbox.controller;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.ServiceResponseDTO;
import me.remind.rest.sandbox.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/externalservice")
public class TestServiceController {

    @Autowired
    TestService testService;

    @RequestMapping({"", "/"})
    public ServiceResponseDTO getServiceResponse() {
        return testService.getTestServiceResult();
    }

}
