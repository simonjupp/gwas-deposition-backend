package uk.ac.ebi.spot.gwas.template.validator.component.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateColumnDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSheetDto;
import uk.ac.ebi.spot.gwas.template.validator.config.ValidatorConstants;
import uk.ac.ebi.spot.gwas.template.validator.domain.CellValidation;
import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(ValidatorConstants.SCHEMA)
public class StudyCellValidationParser implements CellValidationParser {


    @Override
    public ValidationConfiguration parseCellValidations(String templateSheetContent) {
        try {
            TemplateSheetDto templateSheetDto = new ObjectMapper().readValue(templateSheetContent, TemplateSheetDto.class);
            ValidationConfiguration validationConfiguration = new ValidationConfiguration();

            validationConfiguration.setStudyTagColumnName(templateSheetDto.getStudyTagColumnName());
            validationConfiguration.setTriggerRow(templateSheetDto.getTriggerRow());

            List<CellValidation> columns = new ArrayList<>();
            for (TemplateColumnDto templateColumnDto : templateSheetDto.getColumns()) {
                CellValidation cellValidation = new CellValidation();
                cellValidation.setColumnHeading(templateColumnDto.getColumnHeading());
                cellValidation.setColumnName(templateColumnDto.getColumnName());
                cellValidation.setBaseType(templateColumnDto.getBaseType());
                cellValidation.setRequired(templateColumnDto.getRequired().booleanValue());
                cellValidation.setPattern(templateColumnDto.getPattern());
                if (templateColumnDto.getLowerBound() != null) {
                    cellValidation.setLowerBound(templateColumnDto.getLowerBound());
                }
                if (templateColumnDto.getUpperBound() != null) {
                    cellValidation.setUpperBound(templateColumnDto.getUpperBound());
                }
                cellValidation.setAcceptedValues(templateColumnDto.getAcceptedValues());
                columns.add(cellValidation);
            }
            validationConfiguration.setColumns(columns);
            return validationConfiguration;
        } catch (IOException e) {
            return null;
        }
    }
}
