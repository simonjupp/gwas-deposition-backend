package uk.ac.ebi.spot.gwas.deposition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfig {

    @Autowired
    private SystemConfigProperties systemConfigProperties;

    @Bean
    public ServletWebServerFactory servletContainer() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        int port = Integer.parseInt(systemConfigProperties.getServerPort());
        factory.setPort(port);
        return factory;
    }
}

