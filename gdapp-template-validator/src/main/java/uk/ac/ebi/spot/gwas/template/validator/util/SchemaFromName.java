package uk.ac.ebi.spot.gwas.template.validator.util;

import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSheetDto;
import uk.ac.ebi.spot.gwas.template.validator.config.ValidatorConstants;

public class SchemaFromName {

    public static TemplateSheetDto fromName(String name, TemplateSchemaDto templateSchemaDto) {
        if (name.equalsIgnoreCase(ValidatorConstants.STUDY)) {
            return templateSchemaDto.getStudy();
        }
        if (name.equalsIgnoreCase(ValidatorConstants.SAMPLE)) {
            return templateSchemaDto.getSample();
        }
        if (name.equalsIgnoreCase(ValidatorConstants.NOTES)) {
            return templateSchemaDto.getNotes();
        }
        if (name.equalsIgnoreCase(ValidatorConstants.ASSOCIATION)) {
            return templateSchemaDto.getAssociation();
        }

        return null;
    }

}
