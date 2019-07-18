package uk.ac.ebi.spot.gwas.deposition.service;

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
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsResponseDto;
import uk.ac.ebi.spot.gwas.deposition.service.impl.RestRequestUtil;
import uk.ac.ebi.spot.gwas.deposition.service.impl.SumStatsServiceImpl;
import uk.ac.ebi.spot.gwas.deposition.util.HttpEntityBuilder;
import uk.ac.ebi.spot.gwas.deposition.util.TestUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SumStatsServiceTest {

    @InjectMocks
    private SumStatsService sumStatsService;

    @Mock
    private RestInteractionConfig restInteractionConfig;

    @Mock
    private RestRequestUtil restRequestUtil;

    @Mock
    private RestTemplate restTemplate;

    private String sumStatsServiceUrl;

    private String sumStatsEndpoint;

    @Before
    public void setup() {
        sumStatsServiceUrl = "https://gwas-sumstats-service";
        sumStatsEndpoint = "/v1/sum-stats";

        sumStatsService = new SumStatsServiceImpl();
        MockitoAnnotations.initMocks(this);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        when(restInteractionConfig.getSumStatsEndpoint()).thenReturn(sumStatsServiceUrl + sumStatsEndpoint);
        when(restRequestUtil.httpEntity()).thenReturn(new HttpEntityBuilder(mockRequest, ""));
    }

    @Test
    public void shouldRetrieveStatsByCallbackId() {
        String callbackId = RandomStringUtils.randomAlphanumeric(5);
        String endpoint = sumStatsServiceUrl + sumStatsEndpoint + "/" + callbackId;

        SummaryStatsResponseDto expected = TestUtil.summaryStatsResponseDto(callbackId);
        ResponseEntity<SummaryStatsResponseDto> response = new ResponseEntity<>(expected, HttpStatus.OK);
        when(restTemplate.exchange(eq(endpoint), eq(HttpMethod.GET), any(), eq(new ParameterizedTypeReference<SummaryStatsResponseDto>() {
        }))).thenReturn(response);

        SummaryStatsResponseDto actual = sumStatsService.retrieveSummaryStatsStatus(callbackId);
        try {
            verify(restTemplate, times(1)).exchange(eq(endpoint),
                    eq(HttpMethod.GET),
                    any(), eq(new ParameterizedTypeReference<SummaryStatsResponseDto>() {
                    }));
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError("A GET request should have been sent to the specified service endpoint !" +
                    " ERROR: " + e.getMessage());
        }

        assertEquals(actual, expected);
    }

    @Test
    public void shouldRegisterForValidation() {
        String callbackId = RandomStringUtils.randomAlphanumeric(5);
        String endpoint = sumStatsServiceUrl + sumStatsEndpoint;

        ResponseEntity<String> response = new ResponseEntity<>(callbackId, HttpStatus.CREATED);
        when(restTemplate.exchange(eq(endpoint), eq(HttpMethod.POST), any(), eq(new ParameterizedTypeReference<String>() {
        }))).thenReturn(response);

        String actual = sumStatsService.registerStatsForProcessing(TestUtil.summaryStatsRequestDto());
        try {
            verify(restTemplate, times(1)).exchange(eq(endpoint),
                    eq(HttpMethod.POST),
                    any(), eq(new ParameterizedTypeReference<String>() {
                    }));
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError("A GET request should have been sent to the specified service endpoint !" +
                    " ERROR: " + e.getMessage());
        }

        assertEquals(callbackId, actual);
    }
}
