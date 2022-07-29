package co.com.sofka.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200/")
                .allowedOrigins("https://game-cards-de8c1.web.app/")
                .allowedMethods("GET","POST","PUT", "DELETE")
                .allowCredentials(true).maxAge(3600);
    }
}