package uk.ac.ebi.spot.gwas.deposition.service;

import org.springframework.web.multipart.MultipartFile;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;

public interface FileUploadsService {

    FileUpload storeFile(MultipartFile file);

    FileUpload getFileUpload(String fileUploadId);

    byte[] retrieveFileContent(String fileUploadId);
}
