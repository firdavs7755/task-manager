package uz.firdavs.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*registry.addMapping("/*")
                .allowedOrigins("*")
                .allowedOriginPatterns("*") // Use allowedOriginPatterns instead of allowedOrigins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);*/
    /*    registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all methods
                .allowedHeaders("*") // Allow all headers
                .exposedHeaders("Authorization") // Allow the Authorization header to be exposed to the client
                .allowCredentials(false) // Allow credentials (e.g., cookies)
                .maxAge(3600); // Max age of the preflight request in seconds (1 hour)
*/
    }
}
