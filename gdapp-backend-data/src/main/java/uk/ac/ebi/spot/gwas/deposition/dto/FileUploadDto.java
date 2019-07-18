package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsStatusDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class FileUploadDto implements Serializable {

    private static final long serialVersionUID = -446148751976288089L;

    @NotEmpty
    @JsonProperty("id")
    private final String id;

    @NotEmpty
    @JsonProperty("fileName")
    private final String fileName;

    @NotNull
    @JsonProperty("fileSize")
    private final Long fileSize;

    @NotEmpty
    @JsonProperty("status")
    private final String status;

    @JsonProperty("summaryStatsStatuses")
    private final List<SummaryStatsStatusDto> summaryStatsStatuses;

    @JsonCreator
    public FileUploadDto(@JsonProperty("id") String id,
                         @JsonProperty("status") String status,
                         @JsonProperty("fileName") String fileName,
                         @JsonProperty("fileSize") Long fileSize,
                         @JsonProperty("summaryStatsStatuses") List<SummaryStatsStatusDto> summaryStatsStatuses) {
        this.id = id;
        this.status = status;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.summaryStatsStatuses = summaryStatsStatuses;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public List<SummaryStatsStatusDto> getSummaryStatsStatuses() {
        return summaryStatsStatuses;
    }
}
