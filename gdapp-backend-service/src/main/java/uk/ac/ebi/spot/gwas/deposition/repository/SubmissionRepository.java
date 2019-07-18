package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends MongoRepository<Submission, String> {

    Optional<Submission> findByIdAndArchived(String id, boolean archived);

    List<Submission> findByArchived(boolean archived);

    List<Submission> findByArchived(boolean archived, Pageable pageable);

    @Query(value = "{'archived': ?0}", count = true)
    Long countByArchived(boolean archived);
}
