package com.seventonine.order;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mengizinkan semua endpoint
                .allowedOrigins("http://localhost:4200") // Mengizinkan akses dari origin ini
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode yang diizinkan
                .allowedHeaders("*") // Header yang diizinkan
                .allowCredentials(true); // Mengizinkan pengiriman kredensial seperti cookies
    }
}
