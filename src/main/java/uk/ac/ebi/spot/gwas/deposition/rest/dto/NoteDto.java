package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class NoteDto implements Serializable {

    private static final long serialVersionUID = -2294082649257083463L;

    @JsonProperty("study_tag")
    private final String studyTag;

    @JsonProperty("note")
    private final String note;

    @JsonProperty("note_subject")
    private final String noteSubject;

    @JsonProperty("status")
    private final String status;

    @JsonCreator
    public NoteDto(@JsonProperty("study_tag") String studyTag,
                   @JsonProperty("note") String note,
                   @JsonProperty("note_subject") String noteSubject,
                   @JsonProperty("status") String status) {
        this.studyTag = studyTag;
        this.note = note;
        this.noteSubject = noteSubject;
        this.status = status;
    }

    public String getStudyTag() {
        return studyTag;
    }

    public String getNote() {
        return note;
    }

    public String getNoteSubject() {
        return noteSubject;
    }

    public String getStatus() {
        return status;
    }

}
