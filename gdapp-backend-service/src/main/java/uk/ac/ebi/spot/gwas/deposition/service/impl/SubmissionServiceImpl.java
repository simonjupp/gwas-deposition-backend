package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedMetadata;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchSubmissions;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.exception.EntityNotFoundException;
import uk.ac.ebi.spot.gwas.deposition.repository.SubmissionRepository;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionService;
import uk.ac.ebi.spot.gwas.deposition.util.BackendUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private static final Logger log = LoggerFactory.getLogger(SubmissionService.class);

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission createSubmission(Submission submission) {
        log.info("Creating submission for publication: {}", submission.getPublicationId());
        submission = submissionRepository.insert(submission);
        log.info("Submission created: {}", submission.getId());

        return submission;
    }

    @Override
    public Submission getSubmission(String submissionId) {
        log.info("Retrieving submission: {}", submissionId);
        Optional<Submission> optionalSubmission = submissionRepository.findByIdAndArchived(submissionId, false);
        if (!optionalSubmission.isPresent()) {
            log.error("Unable to find submission: {}", submissionId);
            throw new EntityNotFoundException("Unable to find submission: " + submissionId);
        }
        log.info("Submission successfully retrieved: {}", optionalSubmission.get().getId());
        return optionalSubmission.get();
    }

    @Override
    public Submission saveSubmission(Submission submission) {
        log.info("Savid submission: {}", submission.getId());
        submission = submissionRepository.save(submission);
        return submission;
    }

    @Override
    public FacetedSearchSubmissions getSubmissions(Integer page, Integer itemsPerPage, String sortType) {
        log.info("Retrieving submissions: {} - {} - {}", page, itemsPerPage, sortType);

        List<Submission> submissions;
        if (page.intValue() == -1 && itemsPerPage == -1) {
            submissions = submissionRepository.findByArchived(false);
            log.info("Retrieved {} submissions.", submissions.size());
        } else {
            Sort.Direction direction = sortType == null ? Sort.Direction.DESC :
                    sortType.equalsIgnoreCase(GWASDepositionBackendConstants.SORT_VALUE_ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page - 1, itemsPerPage, direction, "created.timestamp");
            submissions = submissionRepository.findByArchived(false, pageable);
            log.info("Pageable: {}", submissions.size());
        }
        log.info("Submissions successfully retrieved: {}", submissions.size());

        int totalItems = submissionRepository.countByArchived(false).intValue();
        int totalPages = (page.intValue() == -1 && itemsPerPage == -1) ? 1 : BackendUtil.computeTotalPages(totalItems, itemsPerPage);
        FacetedMetadata facetedMetadata = new FacetedMetadata(page, itemsPerPage,
                totalPages, totalItems, new HashMap<>());
        log.info("Found {} total submissions with this criteria.", totalItems);
        return new FacetedSearchSubmissions(facetedMetadata, submissions);
    }

    @Override
    public void deleteSubmission(String submissionId) {
        log.info("Deleting submission: {}", submissionId);
        Submission submission = this.getSubmission(submissionId);
        submission.setArchived(true);
        submissionRepository.save(submission);
    }

    @Override
    public Submission updateSubmissionStatus(String submissionId, String status) {
        log.info("Updating status [{}] for submission : {}", status, submissionId);
        Submission submission = this.getSubmission(submissionId);
        submission.setStatus(status);
        submission = submissionRepository.save(submission);
        return submission;
    }
}
