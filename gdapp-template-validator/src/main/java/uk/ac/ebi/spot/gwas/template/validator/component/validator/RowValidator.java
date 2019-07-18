package uk.ac.ebi.spot.gwas.template.validator.component.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import uk.ac.ebi.spot.gwas.template.validator.config.ErrorType;
import uk.ac.ebi.spot.gwas.template.validator.domain.CellValidation;
import uk.ac.ebi.spot.gwas.template.validator.domain.ErrorMessage;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RowValidator {

    public final static String BOOL_VALUE_YES = "yes";

    public final static String BOOL_VALUE_NO = "no";

    private List<CellValidation> columns;

    private String studyTag;

    private boolean valid;

    private Map<String, ErrorMessage> errorMessageMap;

    public RowValidator(Row row, List<CellValidation> columns, String studyTagColumnName) {
        this.columns = columns;
        this.valid = true;
        this.errorMessageMap = new LinkedHashMap<>();
        this.processRow(row, studyTagColumnName);
    }

    private void processRow(Row currentRow, String studyTagColumnName) {
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = currentRow.getCell(i);
            CellValidation cellValidation = columns.get(i);

            if (cellValidation.getBaseType().equalsIgnoreCase(String.class.getSimpleName())) {
                String value = null;
                if (cellValidation.isRequired()) {
                    try {
                        value = cell.getStringCellValue();
                    } catch (Exception e) {
                        valid = false;
                        errorMessageMap.put(cellValidation.getColumnHeading(),
                                new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                    }
                    if (value == null || "".equals(value)) {
                        valid = false;
                        errorMessageMap.put(cellValidation.getColumnHeading(),
                                new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                    }
                }
                if (cellValidation.getAcceptedValues() != null) {
                    if (value != null) {
                        if (!cellValidation.getAcceptedValues().contains(value.toLowerCase())) {
                            valid = false;
                            errorMessageMap.put(cellValidation.getColumnHeading(),
                                    new ErrorMessage(ErrorType.INCORRECT_VALUE_RANGE,
                                            ErrorType.ACCEPTED_VALUES, StringUtils.join(cellValidation.getAcceptedValues(), "; ")));
                        }
                    }
                }
                if (cellValidation.getPattern() != null) {
                    if (value != null) {
                        if (!value.matches(cellValidation.getPattern())) {
                            valid = false;
                            errorMessageMap.put(cellValidation.getColumnHeading(),
                                    new ErrorMessage(ErrorType.INCORRECT_VALUE_RANGE,
                                            ErrorType.PATTERN, cellValidation.getPattern()));
                        }
                    }
                }
                if (cellValidation.getColumnName().equalsIgnoreCase(studyTagColumnName)) {
                    if (value != null) {
                        studyTag = value;
                    }
                }
            } else {
                if (cellValidation.getBaseType().equalsIgnoreCase(Double.class.getSimpleName()) ||
                        cellValidation.getBaseType().equalsIgnoreCase(Integer.class.getSimpleName())) {
                    Double value = null;
                    if (cellValidation.isRequired()) {
                        try {
                            String sVal = cell.getStringCellValue();
                            if (sVal == null || "".equals(sVal)) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                            }
                        } catch (Exception e) {
                        }

                        try {
                            value = cell.getNumericCellValue();
                        } catch (Exception e) {
                            valid = false;
                            errorMessageMap.put(cellValidation.getColumnHeading(),
                                    new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                        }
                    }
                    if (value != null) {
                        if (cellValidation.getLowerBound() != null) {
                            if (value < cellValidation.getLowerBound()) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.INCORRECT_VALUE_RANGE,
                                                ErrorType.RANGE, cellValidation.getLowerBound() + "-" + cellValidation.getUpperBound()));
                            }
                        }
                        if (cellValidation.getUpperBound() != null) {
                            if (value > cellValidation.getUpperBound()) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.INCORRECT_VALUE_RANGE,
                                                ErrorType.RANGE, cellValidation.getLowerBound() + "-" + cellValidation.getUpperBound()));
                            }
                        }
                    }
                } else {
                    if (cellValidation.getBaseType().equalsIgnoreCase(Boolean.class.getSimpleName())) {
                        String value = null;
                        if (cellValidation.isRequired()) {
                            try {
                                value = cell.getStringCellValue();
                            } catch (Exception e) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                            }
                            if (value == null || "".equals(value)) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.MISSING_VALUE, null, null));
                            }
                        }
                        if (value != null) {
                            if (!value.equalsIgnoreCase(BOOL_VALUE_YES) && !value.equalsIgnoreCase(BOOL_VALUE_NO)) {
                                valid = false;
                                errorMessageMap.put(cellValidation.getColumnHeading(),
                                        new ErrorMessage(ErrorType.INCORRECT_VALUE_RANGE,
                                                ErrorType.ACCEPTED_VALUES, StringUtils.join(Arrays.asList(new String[]{"Yes", "No"}), "; ")));
                            }
                        }
                    }
                }
            }
        }
    }

    public String getStudyTag() {
        return studyTag;
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, ErrorMessage> getErrorMessageMap() {
        return errorMessageMap;
    }
}
