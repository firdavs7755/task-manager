package uz.firdavs.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by: Firdavsbek
 * Date:       07.04.2024
 * Time:       0:38
 * Project:    task-manager
 */

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8090") // Specify allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false);

//        .allowCredentials(false); todo localda false turadi
/*
        registry.addMapping("/**")
                .allowedMethods("GET")
                .allowedOrigins("http://localhost:8080", "http://localhost:8090")
//                .allowedOrigins("*")
                .allowedHeaders("X-Request-Id", "Authorization")
                .allowCredentials(false)
                .exposedHeaders("abc")
                .maxAge(36L);*/

    }
}
