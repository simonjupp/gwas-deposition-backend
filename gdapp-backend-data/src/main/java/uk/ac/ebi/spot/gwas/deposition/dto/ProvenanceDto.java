package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import uk.ac.ebi.spot.gwas.deposition.util.JsonJodaDateTimeDeserializer;
import uk.ac.ebi.spot.gwas.deposition.util.JsonJodaDateTimeSerializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProvenanceDto implements Serializable {

    private static final long serialVersionUID = 7978459693400159232L;

    @NotNull
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonProperty("timestamp")
    private final DateTime timestamp;

    @NotNull
    @JsonProperty("user")
    private final UserDto user;

    @JsonCreator
    public ProvenanceDto(@JsonProperty("user") UserDto user,
                         @JsonProperty("timestamp") @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class) DateTime timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public UserDto getUser() {
        return user;
    }
}
