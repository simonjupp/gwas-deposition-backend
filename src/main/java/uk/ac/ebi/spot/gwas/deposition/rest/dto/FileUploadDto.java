package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class FileUploadDto implements Serializable {

    private static final long serialVersionUID = -446148751976288089L;

    @NotBlank
    @JsonProperty("id")
    private final String id;

    @NotBlank
    @JsonProperty("fileName")
    private final String fileName;

    @NotBlank
    @JsonProperty("fileSize")
    private final Integer fileSize;

    @NotBlank
    @JsonProperty("status")
    private final String status;

    @JsonProperty("summaryStatsStatuses")
    private final List<SummaryStatsStatusDto> summaryStatsStatuses;

    @JsonCreator
    public FileUploadDto(@JsonProperty("id") String id,
                         @JsonProperty("status") String status,
                         @JsonProperty("fileName") String fileName,
                         @JsonProperty("fileSize") Integer fileSize,
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

    public Integer getFileSize() {
        return fileSize;
    }

    public List<SummaryStatsStatusDto> getSummaryStatsStatuses() {
        return summaryStatsStatuses;
    }
}
