package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchSubmissions;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;

public interface SubmissionService {

    Submission createSubmission(Submission submission);

    Submission getSubmission(String submissionId);

    Submission saveSubmission(Submission submission);

    FacetedSearchSubmissions getSubmissions(Integer page, Integer itemsPerPage, String sortType);

    void deleteSubmission(String submissionId);

    Submission updateSubmissionStatus(String submissionId, String status);
}
