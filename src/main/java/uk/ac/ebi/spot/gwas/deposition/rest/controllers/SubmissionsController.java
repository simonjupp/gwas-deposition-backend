package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.Provenance;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionCreationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.ProvenanceDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.JWTService;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionService;
import uk.ac.ebi.spot.gwas.deposition.service.UserService;
import uk.ac.ebi.spot.gwas.deposition.util.HeadersUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
}
