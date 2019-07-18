package uk.ac.ebi.spot.gwas.deposition.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationDto;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationsResultDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.PublicationDtoAssembler;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class PublicationsControllerTest extends IntegrationTest {

    /**
     * GET /v1/publications/{publicationId}
     */
    @Ignore
    @Test
    public void shouldGetPublicationById() throws Exception {
        String response = mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS + "/" + publication.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PublicationDto actual = mapper.readValue(response, new TypeReference<PublicationDto>() {
        });

        assertEquals(PublicationDtoAssembler.assemble(publication), actual);
    }

    /**
     * GET /v1/publications/{publicationId}?pmid=true
     */
    @Test
    @Ignore
    public void shouldGetPublicationByPMID() throws Exception {
        String response = mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS + "/" + publication.getPmid() +
                "?" + GWASDepositionBackendConstants.PARAM_PMID + "=true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PublicationDto actual = mapper.readValue(response, new TypeReference<PublicationDto>() {
        });

        assertEquals(PublicationDtoAssembler.assemble(publication), actual);
    }

    /**
     * GET /v1/publications/{publicationId}
     */
    @Ignore
    @Test
    public void shouldNotFindGetPublicationById() throws Exception {
        mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS + "/" + RandomStringUtils.randomAlphanumeric(10))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * GET /v1/publications
     */
    @Test
    @Ignore
    public void shouldAllPublications() throws Exception {
        String response = mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PublicationsResultDto actual = mapper.readValue(response, new TypeReference<PublicationsResultDto>() {
        });

        assertEquals(1, actual.getMetadata().getTotalItems().intValue());
        assertEquals(1, actual.getResults().size());
        assertEquals(PublicationDtoAssembler.assemble(publication), actual.getResults().get(0));
    }

}
