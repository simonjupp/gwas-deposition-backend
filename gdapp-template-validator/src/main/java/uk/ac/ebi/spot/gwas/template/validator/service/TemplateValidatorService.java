package uk.ac.ebi.spot.gwas.template.validator.service;

import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationOutcome;
import uk.ac.ebi.spot.gwas.template.validator.util.SubmissionTemplateReader;

public interface TemplateValidatorService {

    ValidationOutcome validate(SubmissionTemplateReader submissionTemplateReader, TemplateSchemaDto templateSchemaDto);

}
