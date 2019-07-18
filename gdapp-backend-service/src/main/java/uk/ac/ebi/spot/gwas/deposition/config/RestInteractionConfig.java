package uk.ac.ebi.spot.gwas.deposition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestInteractionConfig {

    @Value("${gwas-sumstats-service.url}")
    private String sumStatsServiceUrl;

    @Value("${gwas-sumstats-service.enabled}")
    private boolean sumStatsServiceEnabled;

    @Value("${gwas-sumstats-service.endpoints.sum-stats}")
    private String sumStatsEndpoint;

    @Value("${gwas-template-service.url}")
    private String templateServiceUrl;

    @Value("${gwas-template-service.enabled}")
    private boolean templateServiceEnabled;

    @Value("${gwas-template-service.endpoints.template-schema}")
    private String templateSchemaEndpoint;

    public String getSumStatsEndpoint() {
        return sumStatsServiceUrl + sumStatsEndpoint;
    }

    public String getTemplateSchemaEndpoint() {
        return templateServiceUrl + templateSchemaEndpoint;
    }

    public boolean isSumStatsServiceEnabled() {
        return sumStatsServiceEnabled;
    }

    public boolean isTemplateServiceEnabled() {
        return templateServiceEnabled;
    }
}
