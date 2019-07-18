package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.dto.SubmissionDto;

public interface SubmissionAssemblyService {

    SubmissionDto assemble(Submission submission);
}