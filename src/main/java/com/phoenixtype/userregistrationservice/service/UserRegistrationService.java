package com.phoenixtype.userregistrationservice.service;

import com.phoenixtype.userregistrationservice.model.UserRegistrationRequest;
import com.phoenixtype.userregistrationservice.model.UserRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {
    String registerUser(UserRegistrationRequest userRegistrationRequest);

    /**

     - Write an API microservice using Spring Boot to simulate user registration. Write and push your code to GitHub so the interviewer can see the development process.

     - Expose REST API to accept a payload of username, password, and IP address.

     - All parameters must not be blank (!= empty and null). Return error messages if not valid

     - Password need to be greater than 8 characters, containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .” Return error messages if not valid

     - Call this end point to get geolocation for the provided IP: IP-API.com - Geolocation API - Documentation - JSON. If the IP is not in Canada, return error message that user is not eligible to register

     - When all validation is passed, return a random uuid and a welcome message with his username and his City Name (resolved using ip-geolocation api)

     - The API need to have OpenAPI specification, no matter what your approach is code first or design first.

     - Project must use Maven or Gradle to build. Generate a spring boot project here: javax.swing.Spring Initializr

     - Need to have JUnit Tests

     **/

}
