package uk.ac.ebi.spot.gwas.template.validator.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ErrorMessagesConfig {

    @Value("${validator.error-messages:error_messages.txt}")
    private String errorMessagesFile;

    private Map<String, String> errorMessages;

    @PostConstruct
    public void initialize() {
        errorMessages = new HashMap<>();
        Resource resource = new ClassPathResource(errorMessagesFile);
        try {
            String content = IOUtils.toString(resource.getInputStream(), "UTF-8");
            String[] lines = content.split("\n");
            for (String line : lines) {
                line = line.trim();
                if (!"".equals(line)) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        errorMessages.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to read message file: " + e.getMessage());
        }
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
