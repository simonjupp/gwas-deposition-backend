package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.FileUpload;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.FileUploadDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsStatusDto;

import java.util.List;

public class FileUploadDtoAssembler {

    public static FileUploadDto assemble(FileUpload fileUpload,
                                         List<SummaryStatsStatusDto> summaryStatsStatuses) {
        return new FileUploadDto(fileUpload.getId(),
                fileUpload.getStatus(),
                fileUpload.getFileName(),
                fileUpload.getFileSize(),
                summaryStatsStatuses);
    }
}
