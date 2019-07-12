package uk.ac.ebi.spot.gwas.deposition.rest.dto.templateschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TemplateSchemaDto implements Serializable {

    private static final long serialVersionUID = 2331251254273811278L;

    @NotEmpty
    @JsonProperty("version")
    private final String version;

    @NotEmpty
    @JsonProperty("studySheet")
    private final TemplateSheetDto studySheet;

    @NotEmpty
    @JsonProperty("templateSheets")
    private final List<TemplateSheetDto> templateSheets;

    @JsonCreator
    public TemplateSchemaDto(@JsonProperty("version") String version,
                             @JsonProperty("studySheet") TemplateSheetDto studySheet,
                             @JsonProperty("templateSheets") List<TemplateSheetDto> templateSheets) {
        this.version = version;
        this.studySheet = studySheet;
        this.templateSheets = templateSheets;
    }

    public String getVersion() {
        return version;
    }

    public TemplateSheetDto getStudySheet() {
        return studySheet;
    }

    public List<TemplateSheetDto> getTemplateSheets() {
        return templateSheets;
    }
}

