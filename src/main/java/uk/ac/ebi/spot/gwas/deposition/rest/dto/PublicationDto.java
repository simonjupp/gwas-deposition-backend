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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PublicationDto implements Serializable {

    private static final long serialVersionUID = 4614819124284486530L;

    @NotEmpty
    @JsonProperty("pmid")
    private final String pmid;

    @NotEmpty
    @JsonProperty("journal")
    private final String journal;

    @NotEmpty
    @JsonProperty("title")
    private final String title;

    @NotEmpty
    @JsonProperty("firstAuthor")
    private final String firstAuthor;

    @NotNull
    @JsonProperty("publicationDate")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private final DateTime publicationDate;

    @NotNull
    @JsonProperty("correspondingAuthor")
    private final CorrespondingAuthorDto correspondingAuthor;

    @NotEmpty
    @JsonProperty("status")
    private final String status;

    @JsonCreator
    public PublicationDto(@JsonProperty("pmid") String pmid,
                          @JsonProperty("title") String title,
                          @JsonProperty("journal") String journal,
                          @JsonProperty("firstAuthor") String firstAuthor,
                          @JsonProperty("publicationDate") @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class) DateTime publicationDate,
                          @JsonProperty("correspondingAuthor") CorrespondingAuthorDto correspondingAuthor,
                          @JsonProperty("status") String status) {
        this.pmid = pmid;
        this.title = title;
        this.journal = journal;
        this.firstAuthor = firstAuthor;
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

    public String getFirstAuthor() {
        return firstAuthor;
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
