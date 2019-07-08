package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;

public interface SubmissionRepository extends MongoRepository<Submission, String> {

}
