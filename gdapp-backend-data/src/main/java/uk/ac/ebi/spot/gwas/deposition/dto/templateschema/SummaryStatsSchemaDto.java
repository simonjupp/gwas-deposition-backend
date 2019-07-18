package uk.ac.ebi.spot.gwas.deposition.dto.templateschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SummaryStatsSchemaDto implements Serializable {

    private static final long serialVersionUID = -8272587693620496719L;

    @NotEmpty
    @JsonProperty("summaryStatsFileColumn")
    private final String summaryStatsFileColumn;

    @NotEmpty
    @JsonProperty("summaryStatsMD5Column")
    private final String summaryStatsMD5Column;

    @JsonProperty("summaryStatsAssemblyColumn")
    private final String summaryStatsAssemblyColumn;

    @JsonCreator
    public SummaryStatsSchemaDto(@JsonProperty("summaryStatsFileColumn") String summaryStatsFileColumn,
                                 @JsonProperty("summaryStatsMD5Column") String summaryStatsMD5Column,
                                 @JsonProperty("summaryStatsAssemblyColumn") String summaryStatsAssemblyColumn) {
        this.summaryStatsFileColumn = summaryStatsFileColumn;
        this.summaryStatsMD5Column = summaryStatsMD5Column;
        this.summaryStatsAssemblyColumn = summaryStatsAssemblyColumn;
    }

    public String getSummaryStatsFileColumn() {
        return summaryStatsFileColumn;
    }

    public String getSummaryStatsMD5Column() {
        return summaryStatsMD5Column;
    }

    public String getSummaryStatsAssemblyColumn() {
        return summaryStatsAssemblyColumn;
    }
}
