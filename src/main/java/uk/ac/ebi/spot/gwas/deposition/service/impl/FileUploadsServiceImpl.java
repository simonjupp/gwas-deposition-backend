package uk.ac.ebi.spot.gwas.deposition.service.impl;

import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.ebi.spot.gwas.deposition.constants.FileUploadStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;
import uk.ac.ebi.spot.gwas.deposition.exception.EntityNotFoundException;
import uk.ac.ebi.spot.gwas.deposition.exception.FileProcessingException;
import uk.ac.ebi.spot.gwas.deposition.repository.FileUploadRepository;
import uk.ac.ebi.spot.gwas.deposition.service.FileUploadsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadsServiceImpl implements FileUploadsService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsService.class);

    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public FileUpload storeFile(MultipartFile file) {
        log.info("Storing new file: {}", file.getName());
        try {
            ObjectId objectId = gridFsOperations.store(file.getInputStream(), file.getName(), file.getContentType());
            log.info("File successfully stored: {}", objectId.toString());
            FileUpload fileUpload = new FileUpload(objectId.toString(), file.getName(),
                    file.getSize(), FileUploadStatus.PROCESSING.name());
            fileUpload = fileUploadRepository.insert(fileUpload);
            log.info("FileUpload object successfully created: {}", fileUpload.getId());
            return fileUpload;
        } catch (IOException e) {
            log.error("Unable to store file [{}]: {}", file.getName(), e.getMessage(), e);
        }
        throw new FileProcessingException("Unable to store file: " + file.getName());
    }

    @Override
    public FileUpload getFileUpload(String fileUploadId) {
        log.info("Retrieving file upload: {}", fileUploadId);
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(fileUploadId);
        if (!optionalFileUpload.isPresent()) {
            log.error("Unable to find file upload: {}", fileUploadId);
            throw new EntityNotFoundException("Unable to find file upload: " + fileUploadId);
        }
        log.info("File upload successfully retrieved: {}", optionalFileUpload.get().getId());
        return optionalFileUpload.get();
    }

    @Override
    public byte[] retrieveFileContent(String fileUploadId) {
        log.info("Retrieving file content: {}", fileUploadId);
        FileUpload fileUpload = getFileUpload(fileUploadId);

        GridFSFile file = getGridFsdbFileForFileId(fileUpload.getFileId());
        byte[] attachmentByteArray;

        try (InputStream inputStream = getFileDownloadStream(file.getObjectId())) {
            attachmentByteArray = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Unable to get file {}: {}", fileUpload.getFileId(), e.getMessage(), e);
            throw new FileProcessingException("Unable to get file " + fileUpload.getFileId()
                    + ": " + e.getMessage());
        }
        log.info("Content for file {} successfully retrieved: {}", fileUpload.getFileId(),
                attachmentByteArray.length);

        return attachmentByteArray;
    }

    @Override
    public List<FileUpload> getFileUploads(List<String> ids) {
        log.info("Retrieving files: {}", ids);
        if (ids == null) {
            return new ArrayList<>();
        }

        List<FileUpload> fileUploads = fileUploadRepository.findByIdIn(ids);
        log.info("Found {} files.", fileUploads.size());
        return fileUploads;
    }

    private GridFSFile getGridFsdbFileForFileId(String fileId) {
        log.info("Received call to get GridFSFile for id {}", fileId);
        Query query = new Query(Criteria.where("_id").is(fileId));
        GridFSFile file = gridFsOperations.findOne(query);
        if (file == null) {
            log.error("No file found in DB for id: {}", fileId);
            throw new EntityNotFoundException("No file found in DB for id: " + fileId);
        }
        return file;
    }

    private InputStream getFileDownloadStream(ObjectId objectId) {
        log.info("Retrieving file for download {}", objectId.toString());
        GridFSDownloadStream stream = GridFSBuckets.create(mongoTemplate.getDb())
                .openDownloadStream(objectId);
        log.info("Retrieved download stream {}", objectId.toString());
        return stream;

    }
}
