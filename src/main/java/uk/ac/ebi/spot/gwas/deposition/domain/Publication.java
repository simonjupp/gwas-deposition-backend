package uk.ac.ebi.spot.gwas.deposition.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publications")
public class Publication {

    @Id
    private String id;

    @Indexed
    private String pmid;

    private String status;

    private String journal;

    private String title;

    private String firstAuthor;

    private DateTime publicationDate;

    private CorrespondingAuthor correspondingAuthor;

    public Publication() {

    }

    public Publication(String pmid, String journal, String title, String firstAuthor,
                       DateTime publicationDate, CorrespondingAuthor correspondingAuthor,
                       String status) {
        this.pmid = pmid;
        this.journal = journal;
        this.title = title;
        this.firstAuthor = firstAuthor;
        this.publicationDate = publicationDate;
        this.correspondingAuthor = correspondingAuthor;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }

    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public CorrespondingAuthor getCorrespondingAuthor() {
        return correspondingAuthor;
    }

    public void setCorrespondingAuthor(CorrespondingAuthor correspondingAuthor) {
        this.correspondingAuthor = correspondingAuthor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
