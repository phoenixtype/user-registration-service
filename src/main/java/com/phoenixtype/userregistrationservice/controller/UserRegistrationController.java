package com.phoenixtype.userregistrationservice.controller;

import com.phoenixtype.userregistrationservice.model.User;
import com.phoenixtype.userregistrationservice.model.UserRegistrationRequest;
import com.phoenixtype.userregistrationservice.model.UserRegistrationResponse;
import com.phoenixtype.userregistrationservice.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
@Tag(name = "User Registration")
@RequestMapping("/api")
public class UserRegistrationController {
    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    SmartValidator smartValidator;

    @PostMapping("/registerCustomer")
    @Operation(summary = "Post method in the controller class for user registration logic", responses = {
            @ApiResponse(description = "Return success and the user registration response message",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRegistrationRequest.class))),
            @ApiResponse(description = "Bad Request",
                    responseCode = "400",
                    content = @Content)
    })
    @ResponseBody
    public ResponseEntity<String> registerCustomer(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {
        log.info("new user registration {}", userRegistrationRequest);
        try {
            validateRequestBody(userRegistrationRequest);
            String userRegistrationResponseString = userRegistrationService.registerUser(userRegistrationRequest);
            return new ResponseEntity<String>(userRegistrationResponseString, HttpStatus.OK);
        }
        catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The has been a problem with your request");

        }
//        validateRequestBody(userRegistrationRequest);
//        String userRegistrationResponseString = userRegistrationService.registerUser(userRegistrationRequest);
//        return new ResponseEntity<String>(userRegistrationResponseString, HttpStatus.OK);
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