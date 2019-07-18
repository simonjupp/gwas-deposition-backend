package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsRequestDto;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsResponseDto;
import uk.ac.ebi.spot.gwas.deposition.service.SumStatsService;

@Service
public class SumStatsServiceImpl extends GatewayService implements SumStatsService {

    private static final Logger log = LoggerFactory.getLogger(SumStatsService.class);

    @Override
    public SummaryStatsResponseDto retrieveSummaryStatsStatus(String callbackId) {
        log.info("Retrieving summary stats status for callback ID: {}", callbackId);
        String endpoint = restInteractionConfig.getSumStatsEndpoint() + "/" + callbackId;

        HttpEntity httpEntity = restRequestUtil.httpEntity()
                .build();
        ResponseEntity<SummaryStatsResponseDto> response =
                restTemplate.exchange(endpoint,
                        HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<SummaryStatsResponseDto>() {
                        });

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("Summary stats status successfully retrieved: {}", response.getBody().getCompleted());
            return response.getBody();
        }

        log.error("Unable to call gwas-sumstats-service: {}", response.getStatusCode());
        return null;
    }

    @Override
    public String registerStatsForProcessing(SummaryStatsRequestDto summaryStatsRequestDto) {
        log.info("Registering summary stats for validation: {}", summaryStatsRequestDto.getRequestEntries().size());
        String endpoint = restInteractionConfig.getSumStatsEndpoint();

        HttpEntity httpEntity = restRequestUtil.httpEntity()
                .withJsonBody(summaryStatsRequestDto)
                .build();
        ResponseEntity<String> response =
                restTemplate.exchange(endpoint,
                        HttpMethod.POST, httpEntity,
                        new ParameterizedTypeReference<String>() {
                        });

        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            log.info("Summary stats status successfully registered: {}", response.getBody());
            return response.getBody();
        }

        log.error("Unable to call gwas-sumstats-service: {}", response.getStatusCode());
        return null;
    }

}
