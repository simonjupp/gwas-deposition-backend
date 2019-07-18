package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;

import java.util.Optional;

public interface PublicationRepository extends MongoRepository<Publication, String> {

    Optional<Publication> findByPmid(String pmid);
}
