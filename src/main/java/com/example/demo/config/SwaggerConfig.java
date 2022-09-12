package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAoi(@Value("${project.version}")String appVersion){

        return new OpenAPI().info(
                new Info().title("Web app").version(appVersion)
        );
    }

}
