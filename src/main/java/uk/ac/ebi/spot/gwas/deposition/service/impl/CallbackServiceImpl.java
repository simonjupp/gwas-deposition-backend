package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.constants.SummaryStatsEntryStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.CallbackId;
import uk.ac.ebi.spot.gwas.deposition.domain.SummaryStatsEntry;
import uk.ac.ebi.spot.gwas.deposition.repository.CallbackIdRepository;
import uk.ac.ebi.spot.gwas.deposition.repository.SummaryStatsEntryRepository;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsResponseDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsStatusDto;
import uk.ac.ebi.spot.gwas.deposition.service.CallbackService;
import uk.ac.ebi.spot.gwas.deposition.service.SumStatsService;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "gwas-template-service.callback-schedule.enabled", havingValue = "true")
public class CallbackServiceImpl implements CallbackService {

    private static final Logger log = LoggerFactory.getLogger(CallbackService.class);

    @Autowired
    private SumStatsService sumStatsService;

    @Autowired
    private CallbackIdRepository callbackIdRepository;

    @Autowired
    private SummaryStatsEntryRepository summaryStatsEntryRepository;

    @Scheduled(cron = "${gwas-template-service.callback-schedule.freq}")
    public void runTask() {
        List<CallbackId> callbackIds = callbackIdRepository.findAll();
        log.info("Running callback check on {} IDs.", callbackIds.size());
        for (CallbackId callbackId : callbackIds) {
            SummaryStatsResponseDto summaryStatsResponseDto = sumStatsService.retrieveSummaryStatsStatus(callbackId.getCallbackId());

            List<SummaryStatsStatusDto> summaryStatsStatusDtos = summaryStatsResponseDto.getStatusList();
            for (SummaryStatsStatusDto summaryStatsStatusDto : summaryStatsStatusDtos) {
                if (!summaryStatsStatusDto.getStatus().equalsIgnoreCase(SummaryStatsEntryStatus.VALIDATING.name())) {
                    Optional<SummaryStatsEntry> summaryStatsEntryOpt = summaryStatsEntryRepository.findById(summaryStatsStatusDto.getId());
                    if (!summaryStatsEntryOpt.isPresent()) {
                        log.error("Unable to find summary stats entry: {}", summaryStatsStatusDto.getId());
                    } else {
                        SummaryStatsEntry summaryStatsEntry = summaryStatsEntryOpt.get();
                        summaryStatsEntry.setError(summaryStatsStatusDto.getError());
                        summaryStatsEntry.setStatus(summaryStatsStatusDto.getStatus());
                        summaryStatsEntryRepository.save(summaryStatsEntry);
                    }
                }
            }
            if (summaryStatsResponseDto.getCompleted().booleanValue()) {
                callbackIdRepository.delete(callbackId);
            }
        }
    }
}
