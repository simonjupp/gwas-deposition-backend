package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationsResultDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -8178973822686574145L;

    @NotEmpty
    @JsonProperty("metadata")
    private final FacetedMetadataDto metadata;

    @JsonProperty("results")
    private final List<PublicationDto> results;

    @JsonCreator
    public PublicationsResultDto(@JsonProperty("metadata") FacetedMetadataDto metadata,
                                 @JsonProperty("results") List<PublicationDto> results) {
        this.metadata = metadata;
        this.results = results;
    }

    public FacetedMetadataDto getMetadata() {
        return metadata;
    }

    public List<PublicationDto> getResults() {
        return results;
    }
}
