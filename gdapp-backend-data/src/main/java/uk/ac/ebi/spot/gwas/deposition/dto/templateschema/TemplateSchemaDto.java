package uk.ac.ebi.spot.gwas.deposition.dto.templateschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TemplateSchemaDto implements Serializable {

    private static final long serialVersionUID = 2331251254273811278L;

    @NotEmpty
    @JsonProperty("version")
    private final String version;

    @NotEmpty
    @JsonProperty("study")
    private final TemplateSheetDto study;

    @NotEmpty
    @JsonProperty("association")
    private final TemplateSheetDto association;

    @NotEmpty
    @JsonProperty("sample")
    private final TemplateSheetDto sample;

    @NotEmpty
    @JsonProperty("notes")
    private final TemplateSheetDto notes;

    @JsonCreator
    public TemplateSchemaDto(@JsonProperty("version") String version,
                             @JsonProperty("study") TemplateSheetDto study,
                             @JsonProperty("association") TemplateSheetDto association,
                             @JsonProperty("sample") TemplateSheetDto sample,
                             @JsonProperty("notes") TemplateSheetDto notes) {
        this.version = version;
        this.study = study;
        this.association = association;
        this.sample = sample;
        this.notes = notes;
    }

    public String getVersion() {
        return version;
    }

    public TemplateSheetDto getStudy() {
        return study;
    }

    public TemplateSheetDto getAssociation() {
        return association;
    }

    public TemplateSheetDto getSample() {
        return sample;
    }

    public TemplateSheetDto getNotes() {
        return notes;
    }
}