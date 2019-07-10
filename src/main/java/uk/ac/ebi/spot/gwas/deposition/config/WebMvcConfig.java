package uk.ac.ebi.spot.gwas.deposition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.ac.ebi.spot.gwas.deposition.interceptor.AuthInterceptor;


public class WebMvcConfig {

    @Configuration
    @Profile({"sandbox"})
    public static class SandboxWebMvcConfig implements WebMvcConfigurer {

        @Bean
        public AuthInterceptor authInterceptor() {
            return new AuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authInterceptor());
        }

        @Bean
        @Order(0)
        public MultipartFilter multipartFilter() {
            MultipartFilter multipartFilter = new MultipartFilter();
            multipartFilter.setMultipartResolverBeanName("filterMultipartResolver");
            return multipartFilter;
        }

        @Bean(name = "filterMultipartResolver")
        public CommonsMultipartResolver multipartResolver() {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            multipartResolver.setMaxUploadSize(30000000);
            return multipartResolver;
        }
    }
}