package uk.ac.ebi.spot.gwas.deposition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GWASDepositionBackendConfig {

    @Value("${gwas-deposition.auth.enabled}")
    private boolean authEnabled;

    public boolean isAuthEnabled() {
        return authEnabled;
    }
}
