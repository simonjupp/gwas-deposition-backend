package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SubmissionCreationDto implements Serializable {

    private static final long serialVersionUID = 3158029215487618051L;

    @NotBlank
    @JsonProperty("publication")
    private final PublicationDto publication;

    @JsonCreator
    public SubmissionCreationDto(@JsonProperty("publication") PublicationDto publication) {
        this.publication = publication;
    }

    public PublicationDto getPublication() {
        return publication;
    }
}
