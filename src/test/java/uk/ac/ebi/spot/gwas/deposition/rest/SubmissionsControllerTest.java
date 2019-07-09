package uk.ac.ebi.spot.gwas.deposition.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionCreationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class SubmissionsControllerTest extends IntegrationTest {

    /**
     * POST /v1/submissions
     */
    @Test
    public void shouldCreateSubmission() throws Exception {
        createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
    }

    private SubmissionDto createSubmission(SubmissionCreationDto submissionCreationDto) throws Exception {
        String response = mockMvc.perform(post(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(submissionCreationDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubmissionDto actual = mapper.readValue(response, new TypeReference<SubmissionDto>() {
        });

        assertEquals(submissionCreationDto.getPublication(), actual.getPublication());
        assertEquals(user.getName(), actual.getCreated().getUser().getName());
        assertEquals(user.getEmail(), actual.getCreated().getUser().getEmail());

        assertNull(actual.getFile());
        assertNull(actual.getStudies());
        assertNull(actual.getSamples());
        assertNull(actual.getAssociations());
        assertNull(actual.getNotes());
        return actual;
    }
}
