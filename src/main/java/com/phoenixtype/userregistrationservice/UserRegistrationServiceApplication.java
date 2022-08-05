package com.phoenixtype.userregistrationservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@EnableSwagger2
public class UserRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationServiceApplication.class, args);
	}

	@Bean
	public OpenAPI openApiConfig(){
		return  new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		Info info = new Info();
		info.
				title("Demo PNC code").
				description("User registration logic backend demo").
				version("v1");
		return info;
	}
}
