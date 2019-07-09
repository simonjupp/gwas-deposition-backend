package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "submissions")
public class Submission {

    @Id
    private String id;

    private String publicationId;

    private String status;

    @Indexed
    private List<String> studies;

    @Indexed
    private List<String> associations;

    @Indexed
    private List<String> samples;

    @Indexed
    private List<String> notes;

    @Indexed
    private List<String> fileUploads;

    private Provenance created;

    public Submission(String publicationId, String status, Provenance created) {
        this.publicationId = publicationId;
        this.status = status;
        this.created = created;

        this.studies = new ArrayList<>();
        this.associations = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.samples = new ArrayList<>();
        this.fileUploads = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provenance getCreated() {
        return created;
    }

    public void setCreated(Provenance created) {
        this.created = created;
    }

    public List<String> getStudies() {
        return studies;
    }

    public void setStudies(List<String> studies) {
        this.studies = studies;
    }
}
