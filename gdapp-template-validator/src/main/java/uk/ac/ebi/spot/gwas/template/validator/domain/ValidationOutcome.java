package uk.ac.ebi.spot.gwas.template.validator.domain;

import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode
public class ValidationOutcome {

    private boolean valid;

    private Map<String, List<String>> errorMessages;

    public ValidationOutcome(boolean valid, Map<String, List<String>> errorMessages) {
        this.valid = valid;
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, List<String>> getErrorMessages() {
        return errorMessages;
    }
}
