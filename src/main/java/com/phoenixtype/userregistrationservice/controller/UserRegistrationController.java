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


/**
 * Delete this
    @PostMapping(value = "/check")
    public String endpoint() {
        String stringVal = "Hello World";
        System.out.println(stringVal);
        return stringVal;
    }
 **/
