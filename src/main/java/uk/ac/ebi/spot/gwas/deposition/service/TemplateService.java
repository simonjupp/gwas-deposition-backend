package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.rest.dto.templateschema.TemplateSchemaDto;

public interface TemplateService {

    TemplateSchemaDto retrieveTemplateSchema(String templateVersion);
}
