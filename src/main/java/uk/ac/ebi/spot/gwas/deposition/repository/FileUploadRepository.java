package uk.ac.ebi.spot.gwas.deposition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;

import java.util.List;

public interface FileUploadRepository extends MongoRepository<FileUpload, String> {
    List<FileUpload> findByIdIn(List<String> ids);
}
