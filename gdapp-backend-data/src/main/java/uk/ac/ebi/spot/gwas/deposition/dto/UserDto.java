package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class UserDto implements Serializable {

    private static final long serialVersionUID = 4908356522704320852L;

    @NotEmpty
    @JsonProperty("name")
    private final String name;

    @NotEmpty
    @JsonProperty("email")
    private final String email;


    @JsonCreator
    public UserDto(@JsonProperty("name") String name,
                   @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
