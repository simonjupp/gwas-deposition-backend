package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.Association;

import java.util.List;

public interface AssociationRepository extends MongoRepository<Association, String> {
    List<Association> findByIdIn(List<String> studies);
}
