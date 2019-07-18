package uk.ac.ebi.spot.gwas.deposition.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.spot.gwas.deposition.config.RestInteractionConfig;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.service.impl.RestRequestUtil;
import uk.ac.ebi.spot.gwas.deposition.service.impl.TemplateServiceImpl;
import uk.ac.ebi.spot.gwas.deposition.util.HttpEntityBuilder;
import uk.ac.ebi.spot.gwas.deposition.util.TestUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TemplateServiceTest {

    @InjectMocks
    private TemplateService templateService;

    @Mock
    private RestInteractionConfig restInteractionConfig;

    @Mock
    private RestRequestUtil restRequestUtil;

    @Mock
    private RestTemplate restTemplate;

    private String templateServiceUrl;

    private String templateSchemaEndpoint;

    @Before
    public void setup() {
        templateServiceUrl = "https://gwas-template-service";
        templateSchemaEndpoint = "/v1/template-schema";

        templateService = new TemplateServiceImpl();
        MockitoAnnotations.initMocks(this);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        when(restInteractionConfig.getTemplateSchemaEndpoint()).thenReturn(templateServiceUrl + templateSchemaEndpoint);
        when(restRequestUtil.httpEntity()).thenReturn(new HttpEntityBuilder(mockRequest, ""));
    }

    @Test
    public void shouldRetrieveTemplateSchema() {
        String version = RandomStringUtils.randomAlphanumeric(5);
        String endpoint = templateServiceUrl + templateSchemaEndpoint + "/" + version;

        TemplateSchemaDto expected = TestUtil.templateSchemaDto();
        ResponseEntity<TemplateSchemaDto> response = new ResponseEntity<>(expected, HttpStatus.OK);
        when(restTemplate.exchange(eq(endpoint), eq(HttpMethod.GET), any(), eq(new ParameterizedTypeReference<TemplateSchemaDto>() {
        }))).thenReturn(response);

        TemplateSchemaDto actual = templateService.retrieveTemplateSchema(version);
        try {
            verify(restTemplate, times(1)).exchange(eq(endpoint),
                    eq(HttpMethod.GET),
                    any(), eq(new ParameterizedTypeReference<TemplateSchemaDto>() {
                    }));
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError("A GET request should have been sent to the specified service endpoint !" +
                    " ERROR: " + e.getMessage());
        }
        assertEquals(actual, expected);
    }

}
