package com.phoenixtype.userregistrationservice.controller;

import com.phoenixtype.userregistrationservice.model.UserRegistrationRequest;
import com.phoenixtype.userregistrationservice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
/**
 * - Write an API microservice using Spring Boot to simulate user registration. Write and push your code to GitHub so the interviewer can see the development process.
 * <p>
 * - Expose REST API to accept a payload of username, password, and IP address.
 * <p>
 * - All parameters must not be blank (!= empty and null). Return error messages if not valid
 * <p>
 * - Password need to be greater than 8 characters, containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .” Return error messages if not valid
 * <p>
 * - Call this end point to get geolocation for the provided IP: IP-API.com - Geolocation API - Documentation - JSON. If the IP is not in Canada, return error message that user is not eligible to register
 * <p>
 * - When all validation is passed, return a random uuid and a welcome message with his username and his City Name (resolved using ip-geolocation api)
 * <p>
 * - The API need to have OpenAPI specification, no matter what your approach is code first or design first.
 * <p>
 * - Project must use Maven or Gradle to build. Generate a spring boot project here: javax.swing.Spring Initializr
 * <p>
 * - Need to have JUnit Tests
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserRegistrationController {
    @Autowired
    UserRegistrationService userRegistrationService;
    @Autowired
    SmartValidator smartValidator;
    @PostMapping
    public void registerCustomer(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {
        log.info("new user registration {}", userRegistrationRequest);
        validateRequestBody(userRegistrationRequest);
        userRegistrationService.registerUser(userRegistrationRequest);
    }
    private <T> void validateRequestBody(T body) throws Exception {
        log.info("Validating request...");
        DataBinder databinder = new DataBinder(body);
        databinder.setValidator(smartValidator);
        databinder.validate();
        if (databinder.getBindingResult().hasErrors()) {
            log.error("Request error :{} ", Optional.of(databinder)
                    .map(DataBinder::getBindingResult)
                    .map(BindingResult::getFieldError)
                    .map(FieldError::getDefaultMessage)
                    .orElse("Request validation failed..."));
            throw new Exception("Invalid data");
        }
    }
}
