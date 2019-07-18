package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.config.GWASDepositionBackendConfig;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.exception.AuthorizationException;
import uk.ac.ebi.spot.gwas.deposition.service.JWTService;

@Service
public class JWTServiceImpl implements JWTService {

    private static final Logger log = LoggerFactory.getLogger(JWTService.class);

    @Autowired
    private GWASDepositionBackendConfig gwasDepositionBackendConfig;

    @Override
    public User extractUser(String jwt) {
        log.info("Auth enabled: {}", gwasDepositionBackendConfig.isAuthEnabled());
        if (gwasDepositionBackendConfig.isAuthEnabled()) {
            //TODO: Implement properly
            if (jwt == null) {
                throw new AuthorizationException("Unauthorised access. JWT missing.");
            }
        }

        return new User("Test User", "test@test.com");
    }
}
