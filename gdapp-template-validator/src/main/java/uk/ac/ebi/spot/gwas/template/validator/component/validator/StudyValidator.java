package uk.ac.ebi.spot.gwas.template.validator.component.validator;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.spot.gwas.template.validator.config.ValidatorConstants;
import uk.ac.ebi.spot.gwas.template.validator.domain.CellValidation;
import uk.ac.ebi.spot.gwas.template.validator.domain.ErrorMessage;
import uk.ac.ebi.spot.gwas.template.validator.domain.Study;
import uk.ac.ebi.spot.gwas.template.validator.domain.SubmissionDocument;
import uk.ac.ebi.spot.gwas.template.validator.util.ErrorMessageTemplateProcessor;

import java.util.List;
import java.util.Map;

@Component(ValidatorConstants.STUDY)
public class StudyValidator extends AbstractTemplateValidator implements TemplateValidator {

    @Autowired
    private ErrorMessageTemplateProcessor errorMessageTemplateProcessor;

    @Override
    public boolean handleValidRow(String studyTag, Map<String, String> studyTags, String sheetName) {
        studyTags.put(studyTag, "");
        return true;
    }

    @Override
    public void convertRow(Row row, List<CellValidation> columns, SubmissionDocument submissionDocument) {
        Study study = new Study();
        convertToObject(study, row, columns, submissionDocument);
        submissionDocument.addStudyEntry(study);
    }

    @Override
    public List<String> processErrorMessages(Map<Integer, String> generalErrorMap, Map<Integer, Map<String, ErrorMessage>> errorMap) {
        return errorMessageTemplateProcessor.process(generalErrorMap, errorMap);
    }

}
