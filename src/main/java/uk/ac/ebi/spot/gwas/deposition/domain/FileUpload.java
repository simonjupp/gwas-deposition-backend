package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "fileUploads")
public class FileUpload {

    @Id
    private String id;

    private String fileId;

    private String fileName;

    private Long fileSize;

    private String status;

    private List<String> summaryStatsStatuses;

    public FileUpload() {

    }

    public FileUpload(String fileId, String fileName, Long fileSize, String status) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.status = status;
        this.summaryStatsStatuses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSummaryStatsStatuses() {
        return summaryStatsStatuses;
    }

    public void setSummaryStatsStatuses(List<String> summaryStatsStatuses) {
        this.summaryStatsStatuses = summaryStatsStatuses;
    }

    public void addSummaryStatsStatus(String summaryStatsStatusId) {
        this.summaryStatsStatuses.add(summaryStatsStatusId);
    }
}
