package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.service.TemplateService;

@Service
public class TemplateServiceImpl extends GatewayService implements TemplateService {

    private static final Logger log = LoggerFactory.getLogger(TemplateService.class);

    @Override
    public TemplateSchemaDto retrieveTemplateSchema(String templateVersion) {
        log.info("Retrieving template schema version: {}", templateVersion);
        String endpoint = restInteractionConfig.getTemplateSchemaEndpoint() + "/" + templateVersion;

        HttpEntity httpEntity = restRequestUtil.httpEntity()
                .build();
        ResponseEntity<TemplateSchemaDto> response =
                restTemplate.exchange(endpoint,
                        HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<TemplateSchemaDto>() {
                        });

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("Schema successfully retrieved: {}", response.getBody().getVersion());
            return response.getBody();
        }

        log.error("Unable to call gwas-template-service: {}", response.getStatusCode());
        return null;
    }
}
