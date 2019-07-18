package uk.ac.ebi.spot.gwas.deposition.dto.templateschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TemplateSheetDto implements Serializable {

    private static final long serialVersionUID = -1022096244575416997L;

    @NotEmpty
    @JsonProperty("triggerRow")
    private final String triggerRow;

    @NotEmpty
    @JsonProperty("studyTagColumnName")
    private final String studyTagColumnName;

    @NotEmpty
    @JsonProperty("columns")
    private final List<TemplateColumnDto> columns;

    @JsonCreator
    public TemplateSheetDto(@JsonProperty("triggerRow") String triggerRow,
                            @JsonProperty("studyTagColumnName") String studyTagColumnName,
                            @JsonProperty("columns") List<TemplateColumnDto> columns) {
        this.triggerRow = triggerRow;
        this.studyTagColumnName = studyTagColumnName;
        this.columns = columns;
    }

    public String getTriggerRow() {
        return triggerRow;
    }

    public String getStudyTagColumnName() {
        return studyTagColumnName;
    }

    public List<TemplateColumnDto> getColumns() {
        return columns;
    }
}
