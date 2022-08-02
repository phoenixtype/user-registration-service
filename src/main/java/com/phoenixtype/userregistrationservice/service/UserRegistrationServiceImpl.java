package com.phoenixtype.userregistrationservice.service;

import com.phoenixtype.userregistrationservice.model.User;
import com.phoenixtype.userregistrationservice.model.UserRegistrationRequest;
import com.phoenixtype.userregistrationservice.model.UserRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserRegistrationServiceImpl {

    @Autowired
    UserRegistrationRequest userRegistrationRequest;

    @Autowired
    User user;

    @Autowired
    UserRegistrationResponse userRegistrationResponse;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private final String uuid = UUID.randomUUID().toString().replace("-", "");

    public String registerUser(UserRegistrationRequest userRegistrationRequest) {

        try {

            //check password validation
            if(isValid(userRegistrationRequest.getPassword())) {
                //call endpoint to get geolocation and if not in canada return error message - User cannot be registered
                //When all validation is passed return uuid and welcome message (with username and city name)

//                userRegistrationResponse.setCity();
                userRegistrationResponse.setUUID(uuid);
            }

        } catch (Exception e) {
            log.info("Invalid Password value, please enter a correct password");
            System.out.println("Invalid Password value, please enter a correct password");
        }

        String response = "Hello " + userRegistrationRequest.getUsername() +
                ", your unique id value is " + userRegistrationResponse.getUUID() +
                " welcome to this demo application, we are sending you updates based on activities in the city of  "
                + userRegistrationResponse.getCity();

        return response;
    }

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**

     - Write an API microservice using Spring Boot to simulate user registration. Write and push your code to GitHub so the interviewer can see the development process.

     - Expose REST API to accept a payload of username, password, and IP address.

     - All parameters must not be blank (!= empty and null). Return error messages if not valid

     - Password need to be greater than 8 characters, containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .” Return error messages if not valid

     - Call this end point to get geolocation for the provided IP: IP-API.com - Geolocation API - Documentation - JSON. If the IP is not in Canada, return error message that user is not eligible to register

     - When all validation is passed, return a random uuid and a welcome message with his username and his City Name (resolved using ip-geolocation api)

     - The API need to have OpenAPI specification, no matter what your approach is code first or design first.

     - //Project must use Maven or Gradle to build. Generate a spring boot project here: javax.swing.Spring Initializr

     - Need to have JUnit Tests

     **/


}
