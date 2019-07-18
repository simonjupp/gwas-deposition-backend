package uk.ac.ebi.spot.gwas.deposition.service;


import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsRequestDto;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsResponseDto;

public interface SumStatsService {

    SummaryStatsResponseDto retrieveSummaryStatsStatus(String callbackId);

    String registerStatsForProcessing(SummaryStatsRequestDto summaryStatsRequestDto);
}
