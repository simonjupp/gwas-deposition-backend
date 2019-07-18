package uk.ac.ebi.spot.gwas.deposition.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.constants.SubmissionStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.dto.*;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.*;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class SubmissionsControllerTest extends IntegrationTest {

    @Autowired
    private SubmissionService submissionService;

    /**
     * POST /v1/submissions
     */
    @Test
    public void shouldCreateSubmission() throws Exception {
        createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
    }

    /**
     * GET /v1/submissions/{submissionId}
     */
    @Test
    public void shouldGetSubmission() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        Submission submission = submissionService.getSubmission(submissionDto.getId());
        submission.setAssociations(Arrays.asList(new String[]{association.getId()}));
        submission.setSamples(Arrays.asList(new String[]{sample.getId()}));
        submission.setStudies(Arrays.asList(new String[]{study.getId()}));
        submission.setNotes(Arrays.asList(new String[]{note.getId()}));
        submissionService.saveSubmission(submission);

        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId();

        String response = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubmissionDto actual = mapper.readValue(response, new TypeReference<SubmissionDto>() {
        });

        assertEquals(1, actual.getAssociations().size());
        AssociationDto associationDto = AssociationDtoAssembler.assemble(association);
        assertEquals(associationDto, actual.getAssociations().get(0));

        assertEquals(1, actual.getNotes().size());
        NoteDto noteDto = NoteDtoAssembler.assemble(note);
        assertEquals(noteDto, actual.getNotes().get(0));

        assertEquals(1, actual.getSamples().size());
        SampleDto sampleDto = SampleDtoAssembler.assemble(sample);
        assertEquals(sampleDto, actual.getSamples().get(0));

        assertEquals(1, actual.getStudies().size());
        StudyDto studyDto = StudyDtoAssembler.assemble(study);
        assertEquals(studyDto, actual.getStudies().get(0));
    }

    /**
     * DELETE /v1/submissions/{submissionId}
     */
    @Test
    public void shouldDeleteSubmission() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId();

        mockMvc.perform(delete(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * PUT /v1/submissions/{submissionId}/submit
     */
    @Test
    public void shouldSubmitSubmission() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId() +
                GWASDepositionBackendConstants.API_SUBMIT;

        String response = mockMvc.perform(put(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubmissionDto actual = mapper.readValue(response, new TypeReference<SubmissionDto>() {
        });

        assertEquals(SubmissionStatus.SUBMITTED.name(), actual.getStatus());
    }

    /**
     * GET /v1/submissions
     */
    @Test
    public void shouldGetSubmissions() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        Submission submission = submissionService.getSubmission(submissionDto.getId());
        submission.setAssociations(Arrays.asList(new String[]{association.getId()}));
        submission.setSamples(Arrays.asList(new String[]{sample.getId()}));
        submission.setStudies(Arrays.asList(new String[]{study.getId()}));
        submission.setNotes(Arrays.asList(new String[]{note.getId()}));
        submissionService.saveSubmission(submission);

        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS;

        String response = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubmissionsResultDto submissionsResultDto = mapper.readValue(response, new TypeReference<SubmissionsResultDto>() {
        });

        assertEquals(1, submissionsResultDto.getResults().size());
        SubmissionDto actual = submissionsResultDto.getResults().get(0);

        assertEquals(1, actual.getAssociations().size());
        AssociationDto associationDto = AssociationDtoAssembler.assemble(association);
        assertEquals(associationDto, actual.getAssociations().get(0));

        assertEquals(1, actual.getNotes().size());
        NoteDto noteDto = NoteDtoAssembler.assemble(note);
        assertEquals(noteDto, actual.getNotes().get(0));

        assertEquals(1, actual.getSamples().size());
        SampleDto sampleDto = SampleDtoAssembler.assemble(sample);
        assertEquals(sampleDto, actual.getSamples().get(0));

        assertEquals(1, actual.getStudies().size());
        StudyDto studyDto = StudyDtoAssembler.assemble(study);
        assertEquals(studyDto, actual.getStudies().get(0));
    }

}
