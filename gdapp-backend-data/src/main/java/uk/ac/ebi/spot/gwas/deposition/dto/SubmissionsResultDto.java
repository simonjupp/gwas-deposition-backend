package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SubmissionsResultDto implements Serializable {

    private static final long serialVersionUID = 1693672384430441290L;

    @NotEmpty
    @JsonProperty("metadata")
    private final FacetedMetadataDto metadata;

    @JsonProperty("results")
    private final List<SubmissionDto> results;

    @JsonCreator
    public SubmissionsResultDto(@JsonProperty("metadata") FacetedMetadataDto metadata,
                                @JsonProperty("results") List<SubmissionDto> results) {
        this.metadata = metadata;
        this.results = results;
    }

    public FacetedMetadataDto getMetadata() {
        return metadata;
    }

    public List<SubmissionDto> getResults() {
        return results;
    }
}
