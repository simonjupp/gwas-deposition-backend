package uk.ac.ebi.spot.gwas.deposition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.ac.ebi.spot.gwas.deposition.interceptor.AuthInterceptor;


public class WebMvcConfig {

    @Configuration
    @Profile({"sandbox"})
    public static class ProdWebMvcConfig implements WebMvcConfigurer {

        @Bean
        public AuthInterceptor authInterceptor() {
            return new AuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authInterceptor());
        }
    }
}