package uk.ac.ebi.spot.gwas.deposition.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.PublicationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class PublicationsControllerTest extends IntegrationTest {

    /**
     * GET /v1/publications/{publicationId}
     */
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
     * GET /v1/publications/{publicationId}
     */
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
    public void shouldAllPublications() throws Exception {
        String response = mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PublicationDto> actual = mapper.readValue(response, new TypeReference<List<PublicationDto>>() {
        });

        assertEquals(1, actual.size());
        assertEquals(PublicationDtoAssembler.assemble(publication), actual.get(0));
    }

    /**
     * GET /v1/publications?pmid=<pmid>
     */
    @Test
    public void shouldGetPublicationByPMID() throws Exception {
        String response = mockMvc.perform(get(GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_PUBLICATIONS +
                "?" + GWASDepositionBackendConstants.PARAM_PMID + "=" + publication.getPmid())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PublicationDto> actual = mapper.readValue(response, new TypeReference<List<PublicationDto>>() {
        });

        assertEquals(1, actual.size());
        assertEquals(PublicationDtoAssembler.assemble(publication), actual.get(0));
    }
}
