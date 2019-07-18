package uk.ac.ebi.spot.gwas.template.validator.component.validator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import uk.ac.ebi.spot.gwas.template.validator.config.ErrorType;
import uk.ac.ebi.spot.gwas.template.validator.domain.CellValidation;
import uk.ac.ebi.spot.gwas.template.validator.domain.ErrorMessage;
import uk.ac.ebi.spot.gwas.template.validator.domain.SubmissionDocument;
import uk.ac.ebi.spot.gwas.template.validator.domain.ValidationConfiguration;

import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractTemplateValidator implements TemplateValidator {

    @Override
    public List<String> validateSheet(Sheet sheet, ValidationConfiguration validationConfiguration, Map<String, String> studyTags) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        boolean ready = false;
        boolean valid = true;
        int count = 1;
        Map<Integer, Map<String, ErrorMessage>> errorMap = new LinkedHashMap<>();
        Map<Integer, String> generalErrorMap = new LinkedHashMap<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isTriggerRow(row, validationConfiguration.getTriggerRow())) {
                ready = true;
                continue;
            }
            if (ready) {
                RowValidator rowValidator = new RowValidator(row, validationConfiguration.getColumns(), validationConfiguration.getStudyTagColumnName());
                valid = valid && rowValidator.isValid();
                if (!valid) {
                    Map<String, ErrorMessage> errors = rowValidator.getErrorMessageMap();
                    if (!errors.isEmpty()) {
                        errorMap.put(count, errors);
                    }
                }

                if (!handleValidRow(rowValidator.getStudyTag(), studyTags, sheet.getSheetName())) {
                    generalErrorMap.put(count, ErrorType.ORPHAN_STUDY);
                    valid = false;
                }

                count++;
            }
        }

        if (valid) {
            return new ArrayList<>();
        } else {
            return processErrorMessages(generalErrorMap, errorMap);
        }
    }

    protected boolean isTriggerRow(Row row, String triggerRowValue) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            try {
                String value = cell.getStringCellValue();
                return value.equalsIgnoreCase(triggerRowValue);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void convertSheet(Sheet sheet, ValidationConfiguration validationConfiguration, SubmissionDocument submissionDocument) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        boolean ready = false;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isTriggerRow(row, validationConfiguration.getTriggerRow())) {
                ready = true;
                continue;
            }
            if (ready) {
                convertRow(row, validationConfiguration.getColumns(), submissionDocument);
            }
        }
    }

    protected void convertToObject(Object object, Row row, List<CellValidation> columns, SubmissionDocument submissionDocument) {
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = row.getCell(i);
            CellValidation cellValidation = columns.get(i);

            try {
                Field field = object.getClass().getDeclaredField(cellValidation.getColumnName());
                field.setAccessible(true);
                if (cellValidation.getBaseType().equalsIgnoreCase(String.class.getSimpleName())) {
                    if (cell != null) {
                        field.set(object, cell.getStringCellValue());
                    }
                } else {
                    if (cellValidation.getBaseType().equalsIgnoreCase(Double.class.getSimpleName()) ||
                            cellValidation.getBaseType().equalsIgnoreCase(Integer.class.getSimpleName())) {
                        if (cell != null) {
                            if (cellValidation.getBaseType().equalsIgnoreCase(Double.class.getSimpleName())) {
                                field.set(object, cell.getNumericCellValue());
                            } else {
                                field.set(object, (int) cell.getNumericCellValue());
                            }
                        }
                    } else {
                        if (cellValidation.getBaseType().equalsIgnoreCase(Boolean.class.getSimpleName())) {
                            if (cell != null) {
                                if (cell.getStringCellValue() != null) {
                                    if (cell.getStringCellValue().equalsIgnoreCase(RowValidator.BOOL_VALUE_YES)) {
                                        field.set(object, true);
                                    } else {
                                        if (cell.getStringCellValue().equalsIgnoreCase(RowValidator.BOOL_VALUE_NO)) {
                                            field.set(object, false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
