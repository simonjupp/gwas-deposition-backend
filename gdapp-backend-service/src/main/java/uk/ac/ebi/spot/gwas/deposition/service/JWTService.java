package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.User;

public interface JWTService {

    User extractUser(String jwt);
}
