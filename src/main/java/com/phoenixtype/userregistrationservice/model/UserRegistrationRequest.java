package com.phoenixtype.userregistrationservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Builder

@Component
public class UserRegistrationRequest {
    //check if not blank (Validation)
    @NotBlank (message = "Username cannot be empty")
    private  String username;
    //check if not blank (Validation)
    @NotBlank (message = "Password cannot be empty")
    private String password;
    //check if not blank (Validation)
    @NotBlank (message = "IPAddress cannot be empty")
    private String IpAddress;

}
