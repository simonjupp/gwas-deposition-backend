package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.domain.*;
import uk.ac.ebi.spot.gwas.deposition.repository.AssociationRepository;
import uk.ac.ebi.spot.gwas.deposition.repository.NoteRepository;
import uk.ac.ebi.spot.gwas.deposition.repository.SampleRepository;
import uk.ac.ebi.spot.gwas.deposition.repository.StudyRepository;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.*;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.*;
import uk.ac.ebi.spot.gwas.deposition.service.FileUploadsService;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionAssemblyService;
import uk.ac.ebi.spot.gwas.deposition.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionAssemblyServiceImpl implements SubmissionAssemblyService {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private FileUploadsService fileUploadsService;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private AssociationRepository associationRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    @Override
    public SubmissionDto assemble(Submission submission) {
        Publication publication = publicationService.retrievePublication(submission.getPublicationId(), true);
        List<FileUpload> fileUploads = fileUploadsService.getFileUploads(submission.getFileUploads());

        List<FileUploadDto> fileUploadDtos = new ArrayList<>();
        for (FileUpload fileUpload : fileUploads) {
            fileUploadDtos.add(FileUploadDtoAssembler.assemble(fileUpload, null));
        }

        List<StudyDto> studyDtos = null;
        if (submission.getStudies() != null) {
            List<Study> studies = studyRepository.findByIdIn(submission.getStudies());
            studyDtos = studies.stream().map(StudyDtoAssembler::assemble).collect(Collectors.toList());
        }

        List<SampleDto> sampleDtos = null;
        if (submission.getSamples() != null) {
            List<Sample> samples = sampleRepository.findByIdIn(submission.getStudies());
            sampleDtos = samples.stream().map(SampleDtoAssembler::assemble).collect(Collectors.toList());
        }

        List<AssociationDto> associationDtos = null;
        if (submission.getAssociations() != null) {
            List<Association> associations = associationRepository.findByIdIn(submission.getStudies());
            associationDtos = associations.stream().map(AssociationDtoAssembler::assemble).collect(Collectors.toList());
        }

        List<NoteDto> noteDtos = null;
        if (submission.getNotes() != null) {
            List<Note> notes = noteRepository.findByIdIn(submission.getStudies());
            noteDtos = notes.stream().map(NoteDtoAssembler::assemble).collect(Collectors.toList());
        }

        return SubmissionDtoAssembler.assemble(submission,
                PublicationDtoAssembler.assemble(publication),
                fileUploadDtos,
                studyDtos,
                sampleDtos,
                associationDtos,
                noteDtos,
                ProvenanceDtoAssembler.assemble(submission.getCreated(), userService.getUser(submission.getCreated().getUserId()))
        );
    }

}
