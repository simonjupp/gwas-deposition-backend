package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.SummaryStatsEntry;

public interface SummaryStatsEntryRepository extends MongoRepository<SummaryStatsEntry, String> {
}
