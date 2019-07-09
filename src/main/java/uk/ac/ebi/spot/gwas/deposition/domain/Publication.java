package uk.ac.ebi.spot.gwas.deposition.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "publications")
public class Publication {

    @Id
    private String id;

    @Indexed
    private String pmid;

    private String status;

    private String journal;

    private String title;

    private List<String> authors;

    private DateTime publicationDate;

    private CorrespondingAuthor correspondingAuthor;

    public Publication() {

    }

    public Publication(String pmid, String journal, String title, List<String> authors,
                       DateTime publicationDate, CorrespondingAuthor correspondingAuthor,
                       String status) {
        this.pmid = pmid;
        this.journal = journal;
        this.title = title;
        this.authors = authors;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
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
