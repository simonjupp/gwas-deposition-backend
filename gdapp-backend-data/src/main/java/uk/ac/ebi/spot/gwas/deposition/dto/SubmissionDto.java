package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SubmissionDto implements Serializable {

    private static final long serialVersionUID = 3158029215487618051L;

    @NotEmpty
    @JsonProperty("id")
    private final String id;

    @NotNull
    @JsonProperty("publication")
    private final PublicationDto publication;

    @NotEmpty
    @JsonProperty("status")
    private final String status;

    @JsonProperty("files")
    private final List<FileUploadDto> files;

    @JsonProperty("studies")
    private final List<StudyDto> studies;

    @JsonProperty("samples")
    private final List<SampleDto> samples;

    @JsonProperty("associations")
    private final List<AssociationDto> associations;

    @JsonProperty("notes")
    private final List<NoteDto> notes;

    @NotNull
    @JsonProperty("created")
    private final ProvenanceDto created;

    @JsonCreator
    public SubmissionDto(@JsonProperty("id") String id,
                         @JsonProperty("publication") PublicationDto publication,
                         @JsonProperty("status") String status,
                         @JsonProperty("files") List<FileUploadDto> files,
                         @JsonProperty("studies") List<StudyDto> studies,
                         @JsonProperty("samples") List<SampleDto> samples,
                         @JsonProperty("associations") List<AssociationDto> associations,
                         @JsonProperty("notes") List<NoteDto> notes,
                         @JsonProperty("created") ProvenanceDto created) {
        this.id = id;
        this.publication = publication;
        this.status = status;
        this.files = files;
        this.studies = studies;
        this.samples = samples;
        this.associations = associations;
        this.notes = notes;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public PublicationDto getPublication() {
        return publication;
    }

    public String getStatus() {
        return status;
    }

    public List<FileUploadDto> getFiles() {
        return files;
    }

    public List<StudyDto> getStudies() {
        return studies;
    }

    public List<SampleDto> getSamples() {
        return samples;
    }

    public List<AssociationDto> getAssociations() {
        return associations;
    }

    public List<NoteDto> getNotes() {
        return notes;
    }

    public ProvenanceDto getCreated() {
        return created;
    }
}
