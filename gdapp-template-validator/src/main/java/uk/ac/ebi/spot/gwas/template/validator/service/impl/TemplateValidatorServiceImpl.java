package uk.ac.ebi.spot.gwas.template.validator.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSheetDto;
import uk.ac.ebi.spot.gwas.template.validator.component.parser.CellValidationParser;
import uk.ac.ebi.spot.gwas.template.validator.component.parser.CellValidationParserAdapterFactory;
import uk.ac.ebi.spot.gwas.template.validator.component.validator.TemplateValidator;
import uk.ac.ebi.spot.gwas.template.validator.component.validator.TemplateValidatorAdapterFactory;
import uk.ac.ebi.spot.gwas.template.validator.config.ValidatorConstants;
import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationConfiguration;
import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationOutcome;
import uk.ac.ebi.spot.gwas.template.validator.service.TemplateValidatorService;
import uk.ac.ebi.spot.gwas.template.validator.util.SchemaFromName;
import uk.ac.ebi.spot.gwas.template.validator.util.SubmissionTemplateReader;

import java.util.*;

@Service
public class TemplateValidatorServiceImpl implements TemplateValidatorService {

    @Autowired
    private TemplateValidatorAdapterFactory templateValidatorAdapterFactory;

    @Autowired
    private CellValidationParserAdapterFactory cellValidationParserAdapterFactory;

    @Override
    public ValidationOutcome validate(SubmissionTemplateReader submissionTemplateReader, TemplateSchemaDto templateSchemaDto) {
        Map<String, List<String>> errors = new LinkedHashMap<>();
        Map<String, String> studyTagMap;
        try {
            studyTagMap = readStudiesSheet(submissionTemplateReader, errors, templateSchemaDto);
        } catch (JsonProcessingException e) {
            return null;
        }

        Iterator<Sheet> sheets = submissionTemplateReader.sheets();
        while (sheets.hasNext()) {
            Sheet sheet = sheets.next();
            List<String> errorList;
            try {
                errorList = processSheet(sheet, sheet.getSheetName(), studyTagMap, SchemaFromName.fromName(sheet.getSheetName(), templateSchemaDto));
            } catch (JsonProcessingException e) {
                return null;
            }
            if (!errorList.isEmpty()) {
                errors.put(sheet.getSheetName(), errorList);
            }
        }

        return new ValidationOutcome(errors.isEmpty(), errors);
    }

    private Map<String, String> readStudiesSheet(SubmissionTemplateReader submissionTemplateReader,
                                                 Map<String, List<String>> errors, TemplateSchemaDto templateSchemaDto) throws JsonProcessingException {
        Iterator<Sheet> sheets = submissionTemplateReader.sheets();

        Map<String, String> studyTagMap = new HashMap<>();
        while (sheets.hasNext()) {
            Sheet sheet = sheets.next();
            if (sheet.getSheetName().equalsIgnoreCase(ValidatorConstants.STUDY)) {
                List<String> errorMap = processSheet(sheet, sheet.getSheetName(), studyTagMap,
                        SchemaFromName.fromName(sheet.getSheetName(), templateSchemaDto));
                if (!errorMap.isEmpty()) {
                    errors.put(sheet.getSheetName(), errorMap);
                }
                break;
            }
        }
        return studyTagMap;
    }

    private List<String> processSheet(Sheet sheet, String validatorComponent, Map<String, String> studyTagMap, TemplateSheetDto templateSheetDto) throws JsonProcessingException {
        TemplateValidator templateValidator = templateValidatorAdapterFactory.getAdapter(validatorComponent);
        CellValidationParser cellValidationParser = cellValidationParserAdapterFactory.getAdapter(ValidatorConstants.SCHEMA);
        ValidationConfiguration validationConfiguration = cellValidationParser.parseCellValidations(new ObjectMapper().writeValueAsString(templateSheetDto));
        return templateValidator.validateSheet(sheet, validationConfiguration, studyTagMap);
    }
}
