package uk.ac.ebi.spot.gwas.deposition.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;
import uk.ac.ebi.spot.gwas.deposition.domain.Submission;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.FileUploadDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionCreationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SubmissionDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.FileUploadDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.FileUploadsService;
import uk.ac.ebi.spot.gwas.deposition.service.SubmissionService;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {IntegrationTest.MockJWTServiceConfig.class})
public class FileUploadsControllerTest extends IntegrationTest {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private FileUploadsService fileUploadsService;

    /**
     * POST /v1/submissions/{submissionId}/uploads
     */
    @Test
    public void shouldUploadFile() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        createFileUpload(submissionDto.getId());
    }

    /**
     * GET /v1/submissions/{submissionId}/uploads/{fileUploadId}
     */
    @Test
    public void shouldGetFileUpload() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        FileUploadDto fileUploadDto = createFileUpload(submissionDto.getId());

        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId() +
                GWASDepositionBackendConstants.API_UPLOADS +
                "/" + fileUploadDto.getId();

        String response = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        FileUploadDto actual = mapper.readValue(response, new TypeReference<FileUploadDto>() {
        });
        assertEquals(fileUploadDto, actual);
    }

    /**
     * GET /v1/submissions/{submissionId}/uploads
     */
    @Test
    public void shouldGetFileUploads() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));
        FileUploadDto fileUploadDto = createFileUpload(submissionDto.getId());

        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId() +
                GWASDepositionBackendConstants.API_UPLOADS;

        String response = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<FileUploadDto> actual = mapper.readValue(response, new TypeReference<List<FileUploadDto>>() {
        });
        assertEquals(1, actual.size());
        assertEquals(fileUploadDto, actual.get(0));
    }

    /**
     * GET /v1/submissions/{submissionId}/uploads
     */
    @Test
    public void shouldGetEmptyFileUploads() throws Exception {
        SubmissionDto submissionDto = createSubmission(new SubmissionCreationDto(PublicationDtoAssembler.assemble(publication)));

        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionDto.getId() +
                GWASDepositionBackendConstants.API_UPLOADS;

        String response = mockMvc.perform(get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<FileUploadDto> actual = mapper.readValue(response, new TypeReference<List<FileUploadDto>>() {
        });
        assertTrue(actual.isEmpty());
    }

    private FileUploadDto createFileUpload(String submissionId) throws Exception {
        String endpoint = GWASDepositionBackendConstants.API_V1 +
                GWASDepositionBackendConstants.API_SUBMISSIONS +
                "/" + submissionId +
                GWASDepositionBackendConstants.API_UPLOADS;

        InputStream fileAsStream = new ClassPathResource("test.pdf").getInputStream();
        MockMultipartFile testFile = new MockMultipartFile("file", "test.pdf",
                "pdf", fileAsStream);
        String response =
                mockMvc.perform(MockMvcRequestBuilders.multipart(endpoint)
                        .file(testFile))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();
        FileUploadDto actual = mapper.readValue(response, new TypeReference<FileUploadDto>() {
        });

        Submission submission = submissionService.getSubmission(submissionId);
        assertEquals(1, submission.getFileUploads().size());
        assertEquals(actual.getId(), submission.getFileUploads().get(0));

        FileUpload fileUpload = fileUploadsService.getFileUpload(actual.getId());
        assertEquals(FileUploadDtoAssembler.assemble(fileUpload, null), actual);

        byte[] fileContent = fileUploadsService.retrieveFileContent(actual.getId());
        assertEquals(fileContent.length, actual.getFileSize().intValue());
        return actual;
    }
}
