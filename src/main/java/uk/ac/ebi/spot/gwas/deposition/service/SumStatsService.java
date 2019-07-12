package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsRequestDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsResponseDto;

public interface SumStatsService {

    SummaryStatsResponseDto retrieveSummaryStatsStatus(String callbackId);

    String registerStatsForProcessing(SummaryStatsRequestDto summaryStatsRequestDto);
}
