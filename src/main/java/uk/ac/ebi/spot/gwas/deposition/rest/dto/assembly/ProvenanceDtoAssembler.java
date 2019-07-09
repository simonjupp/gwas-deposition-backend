package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.Provenance;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.ProvenanceDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.UserDto;

public class ProvenanceDtoAssembler {

    public static ProvenanceDto assemble(Provenance provenance, User user) {
        return new ProvenanceDto(new UserDto(user.getName(), user.getEmail()), provenance.getTimestamp());
    }
}
