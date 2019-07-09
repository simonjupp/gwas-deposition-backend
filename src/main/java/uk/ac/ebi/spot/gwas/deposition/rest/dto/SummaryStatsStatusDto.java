package uk.ac.ebi.spot.gwas.deposition.rest.dto;

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
    @JsonProperty("filePath")
    private final String filePath;

    @NotEmpty
    @JsonProperty("status")
    private final String status;

    @JsonCreator
    public SummaryStatsStatusDto(@JsonProperty("filePath") String filePath,
                                 @JsonProperty("status") String status) {
        this.filePath = filePath;
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStatus() {
        return status;
    }
}
