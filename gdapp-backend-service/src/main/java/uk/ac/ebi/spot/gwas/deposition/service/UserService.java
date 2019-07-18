package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.User;

public interface UserService {

    User findOrCreateUser(User user);

    User getUser(String userId);
}
