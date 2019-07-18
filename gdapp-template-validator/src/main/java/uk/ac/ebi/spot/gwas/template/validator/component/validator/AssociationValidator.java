package uk.ac.ebi.spot.gwas.template.validator.component.validator;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.spot.gwas.template.validator.config.ValidatorConstants;
import uk.ac.ebi.spot.gwas.template.validator.domain.Association;
import uk.ac.ebi.spot.gwas.template.validator.domain.CellValidation;
import uk.ac.ebi.spot.gwas.template.validator.domain.ErrorMessage;
import uk.ac.ebi.spot.gwas.template.validator.domain.SubmissionDocument;
import uk.ac.ebi.spot.gwas.template.validator.util.ErrorMessageTemplateProcessor;

import java.util.List;
import java.util.Map;

@Component(ValidatorConstants.ASSOCIATION)
public class AssociationValidator extends AbstractTemplateValidator implements TemplateValidator {

    @Autowired
    private ErrorMessageTemplateProcessor errorMessageTemplateProcessor;

    @Override
    public boolean handleValidRow(String studyTag, Map<String, String> studyTags, String sheetName) {
        if (!studyTags.containsKey(studyTag)) {
            return false;
        }

        return true;
    }

    @Override
    public void convertRow(Row row, List<CellValidation> columns, SubmissionDocument submissionDocument) {
        Association association = new Association();
        convertToObject(association, row, columns, submissionDocument);
        submissionDocument.addAssociation(association);
    }

    @Override
    public List<String> processErrorMessages(Map<Integer, String> generalErrorMap, Map<Integer, Map<String, ErrorMessage>> errorMap) {
        return errorMessageTemplateProcessor.process(generalErrorMap, errorMap);
    }

}
