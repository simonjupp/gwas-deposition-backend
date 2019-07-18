package uk.ac.ebi.spot.gwas.template.validator.component.parser;

import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationConfiguration;

public interface CellValidationParser {

    ValidationConfiguration parseCellValidations(String content);
}
