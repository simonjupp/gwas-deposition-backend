package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "summaryStats")
public class SummaryStatsStatus {

    @Id
    private String id;

    @Indexed
    private String callBackId;

    private String filePath;

    private String status;

    public SummaryStatsStatus() {

    }

    public SummaryStatsStatus(String callBackId, String filePath, String status) {
        this.callBackId = callBackId;
        this.filePath = filePath;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallBackId() {
        return callBackId;
    }

    public void setCallBackId(String callBackId) {
        this.callBackId = callBackId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
