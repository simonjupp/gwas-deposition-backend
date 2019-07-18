package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.dto.*;

import java.util.List;

public class SubmissionDtoAssembler {

    public static SubmissionDto assemble(Submission submission,
                                         PublicationDto publication,
                                         List<FileUploadDto> fileUploads,
                                         List<StudyDto> studies,
                                         List<SampleDto> samples,
                                         List<AssociationDto> associations,
                                         List<NoteDto> notes,
                                         ProvenanceDto created) {
        return new SubmissionDto(submission.getId(),
                publication,
                submission.getStatus(),
                fileUploads,
                studies,
                samples,
                associations,
                notes,
                created
        );
    }
}
