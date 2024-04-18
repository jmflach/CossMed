package coss.med.CossMed.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Coss.Med API")
                        .description(
                                "Rest API for the Coss.Med application. Contains the CRUD functionalities for doctors and patients and for making and cancelling appointments.")
                        .contact(new Contact()
                                .name("João Flach")
                                .email("joao.flach@coss.med"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://coss.med/api/licenca")));
    }

}