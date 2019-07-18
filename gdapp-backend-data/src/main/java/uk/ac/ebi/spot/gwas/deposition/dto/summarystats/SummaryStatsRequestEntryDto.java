package uk.ac.ebi.spot.gwas.deposition.dto.summarystats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class SummaryStatsRequestEntryDto implements Serializable {

    private static final long serialVersionUID = -3048297682018905381L;

    @NotEmpty
    @JsonProperty("id")
    private final String id;

    @NotEmpty
    @JsonProperty("filePath")
    private final String filePath;

    @NotEmpty
    @JsonProperty("md5")
    private final String md5;

    @JsonProperty("assembly")
    private final String assembly;

    @JsonCreator
    public SummaryStatsRequestEntryDto(@JsonProperty("id") String id,
                                       @JsonProperty("filePath") String filePath,
                                       @JsonProperty("md5") String md5,
                                       @JsonProperty("assembly") String assembly) {
        this.id = id;
        this.filePath = filePath;
        this.md5 = md5;
        this.assembly = assembly;
    }

    public String getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMd5() {
        return md5;
    }

    public String getAssembly() {
        return assembly;
    }
}
