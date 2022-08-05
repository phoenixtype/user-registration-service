package com.phoenixtype.userregistrationservice.service;

import com.phoenixtype.userregistrationservice.model.UserRegistrationRequest;
import com.phoenixtype.userregistrationservice.model.UserRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@Service
@Slf4j
public class UserRegistrationService {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private final String uuid = UUID.randomUUID().toString().replace("-", "");
    @Autowired
    UserRegistrationResponse userRegistrationResponse;

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String registerUser(UserRegistrationRequest userRegistrationRequest) throws Exception {
        String response = null;

        try {
            //check password validation
            if (isValid(userRegistrationRequest.getPassword())) {
                //call endpoint to get geolocation and if not in canada return error message - User cannot be registered
                log.info("isValid true?" + userRegistrationRequest);
                log.info("password: " + userRegistrationRequest.getPassword());
                log.info("userRegistrationResponse: " + userRegistrationResponse);

                RestTemplate restTemplate = new RestTemplate();
                userRegistrationResponse = restTemplate.getForObject("http://ip-api.com/json", UserRegistrationResponse.class);
                userRegistrationResponse.setUUID(uuid);
                log.info("userRegistrationResponse: " + userRegistrationResponse);
                try {
                    //When all validation is passed return uuid and welcome message (with username and city name)
                    if (userRegistrationResponse.getCountry().contains("Canada")) {
                        response = "Hello " + userRegistrationRequest.getUsername() +
                                ", your unique one time ID is " + userRegistrationResponse.getUUID() +
                                " and welcome to this demo application, we are sending you updates based on activities in the city of "
                                + userRegistrationResponse.getCity();
                        log.info(response);
                    } else {
                        throw new Exception();
                    }
                }
                catch (Exception e) {
                    response = "Service only available in Canada";
                    return response;
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            response = "Invalid Password value, please enter a correct password";
            return response;
        }
        return response;
    }
}