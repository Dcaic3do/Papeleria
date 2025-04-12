package com.example.Papeleria.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI  customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Papeleria")
                        .version("1.0")
                        .description("Simulacion funcionamiento de una papeleria")
                        .contact(new Contact()
                                .name("Soporte APÏ")
                                .email("dfelipecaicedo@ucundinamarca.edu.co")));

    }
}
