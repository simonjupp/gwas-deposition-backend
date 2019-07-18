package uk.ac.ebi.spot.gwas.deposition.dto.summarystats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SummaryStatsResponseDto implements Serializable {

    private static final long serialVersionUID = -7919945588268541457L;

    @NotEmpty
    @JsonProperty("callbackId")
    private final String callbackId;

    @NotEmpty
    @JsonProperty("completed")
    private final Boolean completed;

    @NotEmpty
    @JsonProperty("statusList")
    private final List<SummaryStatsStatusDto> statusList;

    @JsonCreator
    public SummaryStatsResponseDto(@JsonProperty("callbackId") String callbackId,
                                   @JsonProperty("completed") Boolean completed,
                                   @JsonProperty("statusList") List<SummaryStatsStatusDto> statusList) {
        this.callbackId = callbackId;
        this.completed = completed;
        this.statusList = statusList;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public List<SummaryStatsStatusDto> getStatusList() {
        return statusList;
    }
}
