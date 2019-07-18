package uk.ac.ebi.spot.gwas.template.validator.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import uk.ac.ebi.spot.gwas.template.validator.config.ErrorMessagesConfig;
import uk.ac.ebi.spot.gwas.template.validator.config.ErrorType;
import uk.ac.ebi.spot.gwas.template.validator.domain.ErrorMessage;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ErrorMessageTemplateProcessor {

    @Autowired
    private ErrorMessagesConfig errorMessagesConfig;

    private TemplateEngine templateEngine;

    @PostConstruct
    public void initialize() {
        templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateEngine.setTemplateResolver(templateResolver);
    }

    public List<String> process(Map<Integer, String> generalErrorMap, Map<Integer, Map<String, ErrorMessage>> errorMap) {
        List<String> result = new ArrayList<>();
        List<Integer> rows = orderRows(generalErrorMap, errorMap);
        Map<String, String> contextMap;
        for (int row : rows) {
            List<String> messages = new ArrayList<>();
            if (errorMap.containsKey(row)) {
                for (String cell : errorMap.get(row).keySet()) {
                    ErrorMessage errorMessage = errorMap.get(row).get(cell);

                    contextMap = new HashMap<>();
                    contextMap.put(ErrorType.CTX_ROW, Integer.toString(row));
                    contextMap.put(ErrorType.CTX_COLUMN, cell);
                    if (errorMessage.getValue() != null) {
                        contextMap.put(ErrorType.CTX_VALUE, errorMessage.getValue());
                    }
                    String messageKey = errorMessage.getSubtype() != null ? errorMessage.getType() + "-" + errorMessage.getSubtype() : errorMessage.getType();
                    messages.add(processMessage(messageKey, contextMap));
                }
            }
            if (generalErrorMap.containsKey(row)) {
                contextMap = new HashMap<>();
                contextMap.put(ErrorType.CTX_ROW, Integer.toString(row));
                messages.add(processMessage(generalErrorMap.get(row), contextMap));
            }

            result.add(StringUtils.join(messages, "; "));
        }
        return result;
    }

    public String processMessage(String messageKey, Map<String, String> contextMap) {
        Context context = new Context();
        for (String key : contextMap.keySet()) {
            String value = contextMap.get(key);
            context.setVariable(key, value);
        }

        return templateEngine.process(errorMessagesConfig.getErrorMessages().get(messageKey), context);
    }

    private List<Integer> orderRows(Map<Integer, String> generalErrorMap, Map<Integer, Map<String, ErrorMessage>> errorMap) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Boolean> sortMap = new TreeMap<>();
        for (int i : generalErrorMap.keySet()) {
            sortMap.put(i, true);
        }
        for (int i : errorMap.keySet()) {
            sortMap.put(i, true);
        }
        for (int i : sortMap.keySet()) {
            result.add(i);
        }
        return result;
    }
}
