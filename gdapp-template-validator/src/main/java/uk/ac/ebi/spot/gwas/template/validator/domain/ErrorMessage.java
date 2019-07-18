package uk.ac.ebi.spot.gwas.template.validator.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ErrorMessage {

    private String type;

    private String subtype;

    private String value;

    public ErrorMessage(String type, String subtype, String value) {
        this.type = type;
        this.subtype = subtype;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getValue() {
        return value;
    }
}
