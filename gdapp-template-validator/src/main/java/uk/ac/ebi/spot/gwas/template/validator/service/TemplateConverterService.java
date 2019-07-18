package uk.ac.ebi.spot.gwas.template.validator.service;

import uk.ac.ebi.spot.gwas.template.validator.domain.SubmissionDocument;
import uk.ac.ebi.spot.gwas.template.validator.util.SubmissionTemplateReader;

public interface TemplateConverterService {

    SubmissionDocument convert(SubmissionTemplateReader submissionTemplateReader);
}
