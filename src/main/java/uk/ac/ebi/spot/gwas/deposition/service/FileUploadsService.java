package uk.ac.ebi.spot.gwas.deposition.service;

import org.springframework.web.multipart.MultipartFile;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;

import java.util.List;

public interface FileUploadsService {

    FileUpload storeFile(MultipartFile file);

    FileUpload getFileUpload(String fileUploadId);

    byte[] retrieveFileContent(String fileUploadId);

    List<FileUpload> getFileUploads(List<String> fileUploads);
}
