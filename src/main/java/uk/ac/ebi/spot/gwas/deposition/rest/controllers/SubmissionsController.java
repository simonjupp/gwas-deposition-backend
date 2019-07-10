package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.constants.SubmissionStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.*;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionCreationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionsResultDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.FacetedMetadataDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.ProvenanceDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.*;
import uk.ac.ebi.spot.gwas.deposition.util.HeadersUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = GWASDepositionBackendConstants.API_V1 + GWASDepositionBackendConstants.API_SUBMISSIONS)
public class SubmissionsController {

    private static final Logger log = LoggerFactory.getLogger(SubmissionsController.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionAssemblyService submissionAssemblyService;

    /**
     * POST /v1/submissions
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SubmissionDto createResponse(@Valid @RequestBody SubmissionCreationDto submissionCreationDto,
                                        HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to create new submission for publication: {}", user.getName(),
                submissionCreationDto.getPublication().getPmid());
        Publication publication = publicationService.retrievePublication(submissionCreationDto.getPublication().getPmid(), false);
        Submission submission = submissionService.createSubmission(new Submission(publication.getId(), new Provenance(DateTime.now(), user.getId())));
        log.info("Returning new submission: {}", submission.getId());

        return new SubmissionDto(submission.getId(), PublicationDtoAssembler.assemble(publication),
                submission.getStatus(), null, null, null, null, null,
                ProvenanceDtoAssembler.assemble(submission.getCreated(), user));
    }

    /**
     * GET /v1/submissions/{submissionId}
     */
    @GetMapping(value = "/{submissionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SubmissionDto getSubmission(@PathVariable String submissionId, HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to retrieve submission: {}", user.getName(), submissionId);
        Submission submission = submissionService.getSubmission(submissionId);
        log.info("Returning submission: {}", submission.getId());
        return submissionAssemblyService.assemble(submission);
    }

    /**
     * DELETE /v1/submissions/{submissionId}
     */
    @DeleteMapping(value = "/{submissionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubmission(@PathVariable String submissionId, HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to delete submission: {}", user.getName(), submissionId);
        submissionService.deleteSubmission(submissionId);
        log.info("Submissions successfully deleted.");
    }

    /**
     * PUT /v1/submissions/{submissionId}/submit
     */
    @PutMapping(value = "/{submissionId}" + GWASDepositionBackendConstants.API_SUBMIT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SubmissionDto updateSubmission(@PathVariable String submissionId, HttpServletRequest request) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to submit submission: {}", user.getName(), submissionId);
        Submission submission = submissionService.updateSubmissionStatus(submissionId, SubmissionStatus.SUBMITTED.name());
        log.info("Submissions successfully updated.");
        return submissionAssemblyService.assemble(submission);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SubmissionsResultDto getSubmissions(HttpServletRequest request,
                                               @RequestParam(
                                                       value = GWASDepositionBackendConstants.PARAM_PAGE,
                                                       required = false,
                                                       defaultValue = "1")
                                                       Integer page,
                                               @RequestParam(
                                                       value = GWASDepositionBackendConstants.PARAM_NO_ITEMS,
                                                       required = false,
                                                       defaultValue = "10")
                                                       Integer itemsPerPage,
                                               @RequestParam(
                                                       value = GWASDepositionBackendConstants.PARAM_SORT,
                                                       required = false)
                                                       String sortType) {
        User user = userService.findOrCreateUser(jwtService.extractUser(HeadersUtil.extractJWT(request)));
        log.info("[{}] Request to retrieve submissions: {} - {} - {}", user.getName(), page, itemsPerPage, sortType);
        FacetedSearchSubmissions facetedSearchSubmissions = submissionService.getSubmissions(page, itemsPerPage, sortType);
        log.info("Returning {} submissions.", facetedSearchSubmissions.getSubmissions().size());
        List<SubmissionDto> submissionDtos = new ArrayList<>();
        for (Submission submission : facetedSearchSubmissions.getSubmissions()) {
            submissionDtos.add(submissionAssemblyService.assemble(submission));
        }
        return new SubmissionsResultDto(FacetedMetadataDtoAssembler.assemble(facetedSearchSubmissions.getFacetedMetadata()), submissionDtos);
    }
}
