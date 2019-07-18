package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.spot.gwas.deposition.constants.SummaryStatsEntryStatus;

@Document(collection = "summaryStatsEntries")
public class SummaryStatsEntry {

    @Id
    private String id;

    @Indexed
    private String fileUploadId;

    private String studyTag;

    private String callbackId;

    private String filePath;

    private String md5;

    private String assembly;

    @Indexed
    private String status;

    private String error;

    public SummaryStatsEntry() {

    }

    public SummaryStatsEntry(String fileUploadId, String studyTag, String filePath, String md5, String assembly) {
        this.fileUploadId = fileUploadId;
        this.studyTag = studyTag;
        this.filePath = filePath;
        this.md5 = md5;
        this.assembly = assembly;
        this.status = SummaryStatsEntryStatus.PENDING.name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(String fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public String getStudyTag() {
        return studyTag;
    }

    public void setStudyTag(String studyTag) {
        this.studyTag = studyTag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }
}
