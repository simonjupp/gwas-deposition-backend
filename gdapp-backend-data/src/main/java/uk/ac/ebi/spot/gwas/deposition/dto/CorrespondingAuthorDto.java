package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CorrespondingAuthorDto implements Serializable {

    private static final long serialVersionUID = 2208690209727460977L;

    @NotEmpty
    @JsonProperty("authorName")
    private final String authorName;

    @JsonProperty("email")
    private final String email;

    @JsonCreator
    public CorrespondingAuthorDto(@JsonProperty("authorName") String authorName,
                                  @JsonProperty("email") String email) {
        this.authorName = authorName;
        this.email = email;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getEmail() {
        return email;
    }
}
