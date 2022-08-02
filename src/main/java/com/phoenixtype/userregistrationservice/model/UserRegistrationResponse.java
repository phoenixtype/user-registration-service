package com.phoenixtype.userregistrationservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Builder

@Component
public class UserRegistrationResponse {
    private String UUID;
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private float lat;
    private float lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
    private String query;

    private String welcomeMessage;

}
