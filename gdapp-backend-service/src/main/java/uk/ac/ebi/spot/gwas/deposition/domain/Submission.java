package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.spot.gwas.deposition.constants.SubmissionStatus;

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

    @Indexed
    private boolean archived;

    public Submission(String publicationId, Provenance created) {
        this.publicationId = publicationId;
        this.status = SubmissionStatus.STARTED.name();
        this.created = created;

        this.studies = new ArrayList<>();
        this.associations = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.samples = new ArrayList<>();
        this.fileUploads = new ArrayList<>();
        this.archived = false;
    }

    public Submission() {

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

    public List<String> getAssociations() {
        return associations;
    }

    public void setAssociations(List<String> associations) {
        this.associations = associations;
    }

    public List<String> getSamples() {
        return samples;
    }

    public void setSamples(List<String> samples) {
        this.samples = samples;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<String> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public void addFileUpload(String fileUpload) {
        this.fileUploads.add(fileUpload);
    }

    public void removeFileUpload(String fileUploadId) {
        if (fileUploads.contains(fileUploadId)) {
            this.fileUploads.remove(fileUploadId);
        }
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
