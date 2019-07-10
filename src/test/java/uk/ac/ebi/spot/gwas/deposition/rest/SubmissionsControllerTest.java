package uk.ac.ebi.spot.gwas.deposition.rest;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionCreationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class SubmissionsControllerTest extends IntegrationTest {

    /**
     * POST /v1/submissions
     */
    @Test
    public void shouldCreateSubmission() throws Exception {
        createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
    }

}
