package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.SummaryStatsStatus;

import java.util.Optional;

public interface SummaryStatsRepository extends MongoRepository<SummaryStatsStatus, String> {

    Optional<SummaryStatsStatus> findByCallBackId(String callBackId);
}
