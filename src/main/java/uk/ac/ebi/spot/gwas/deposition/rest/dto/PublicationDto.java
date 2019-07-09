package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import uk.ac.ebi.spot.gwas.deposition.util.JsonJodaDateTimeDeserializer;
import uk.ac.ebi.spot.gwas.deposition.util.JsonJodaDateTimeSerializer;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PublicationDto implements Serializable {

    private static final long serialVersionUID = 4614819124284486530L;

    @NotBlank
    @JsonProperty("pmid")
    private final String pmid;

    @NotBlank
    @JsonProperty("journal")
    private final String journal;

    @NotBlank
    @JsonProperty("title")
    private final String title;

    @NotBlank
    @JsonProperty("authors")
    private final List<String> authors;

    @NotBlank
    @JsonProperty("publicationDate")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private final DateTime publicationDate;

    @NotBlank
    @JsonProperty("correspondingAuthor")
    private final CorrespondingAuthorDto correspondingAuthor;

    @NotBlank
    @JsonProperty("status")
    private final String status;

    @JsonCreator
    public PublicationDto(@JsonProperty("pmid") String pmid,
                          @JsonProperty("title") String title,
                          @JsonProperty("journal") String journal,
                          @JsonProperty("authors") List<String> authors,
                          @JsonProperty("publicationDate") @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class) DateTime publicationDate,
                          @JsonProperty("correspondingAuthor") CorrespondingAuthorDto correspondingAuthor,
                          @JsonProperty("status") String status) {
        this.pmid = pmid;
        this.title = title;
        this.journal = journal;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.correspondingAuthor = correspondingAuthor;
        this.status = status;
    }

    public String getPmid() {
        return pmid;
    }

    public String getJournal() {
        return journal;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public CorrespondingAuthorDto getCorrespondingAuthor() {
        return correspondingAuthor;
    }

    public String getStatus() {
        return status;
    }
}
