package uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SummaryStatsStatusDto implements Serializable {

    private static final long serialVersionUID = -2657456402979135393L;

    @NotEmpty
    @JsonProperty("id")
    private final String id;

    @NotEmpty
    @JsonProperty("status")
    private final String status;

    @JsonProperty("error")
    private final String error;

    @JsonCreator
    public SummaryStatsStatusDto(@JsonProperty("id") String id,
                                 @JsonProperty("status") String status,
                                 @JsonProperty("error") String error) {
        this.id = id;
        this.status = status;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
