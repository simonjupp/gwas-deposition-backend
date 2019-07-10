package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.FileUploadDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.FileUploadDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.FileUploadsService;
import uk.ac.ebi.spot.gwas.deposition.service.JWTService;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionService;
import uk.ac.ebi.spot.gwas.deposition.service.UserService;
import uk.ac.ebi.spot.gwas.deposition.util.HeadersUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = GWASDepositionBackendConstants.API_V1 + GWASDepositionBackendConstants.API_SUBMISSIONS)
public class FileUploadsController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsController.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadsService fileUploadsService;

    @Autowired
    private SubmissionService submissionService;

    /*
     * POST /v1/submissions/{submissionId}/uploads
     */
    @PostMapping(
            value = "/{submissionId}" + GWASDepositionBackendConstants.API_UPLOADS,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FileUploadDto uploadFile(@RequestParam MultipartFile file,
                                    @PathVariable String submissionId,
                                    HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to upload a new file [{}] to submission: {}", user.getName(), file.getOriginalFilename(), submissionId);
        Submission submission = submissionService.getSubmission(submissionId);
        FileUpload fileUpload = fileUploadsService.storeFile(file);
        submission.addFileUpload(fileUpload.getId());
        submissionService.saveSubmission(submission);
        return FileUploadDtoAssembler.assemble(fileUpload, null);
    }

    /**
     * GET /v1/submissions/{submissionId}/uploads/{fileUploadId}
     */
    @GetMapping(value = "/{submissionId}" + GWASDepositionBackendConstants.API_UPLOADS + "/{fileUploadId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FileUploadDto getFileUploadId(@PathVariable String submissionId,
                                         @PathVariable String fileUploadId, HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to retrieve file [{}] from submission: {}", user.getName(), fileUploadId, submissionId);
        Submission submission = submissionService.getSubmission(submissionId);
        FileUpload fileUpload = fileUploadsService.getFileUpload(fileUploadId);
        log.info("Returning file [{}] for submission: {}", fileUpload.getFileName(), submission.getId());
        return FileUploadDtoAssembler.assemble(fileUpload, null);
    }

    /**
     * GET /v1/submissions/{submissionId}/uploads
     */
    @GetMapping(value = "/{submissionId}" + GWASDepositionBackendConstants.API_UPLOADS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<FileUploadDto> getFileUploads(@PathVariable String submissionId, HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to retrieve files from submission: {}", user.getName(), submissionId);
        Submission submission = submissionService.getSubmission(submissionId);
        List<FileUpload> fileUploads = fileUploadsService.getFileUploads(submission.getFileUploads());
        log.info("Returning {} files for submission: {}", fileUploads.size(), submission.getId());
        List<FileUploadDto> result = new ArrayList<>();
        for (FileUpload fileUpload : fileUploads) {
            result.add(FileUploadDtoAssembler.assemble(fileUpload, null));
        }
        return result;
    }
}
