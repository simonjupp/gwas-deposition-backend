package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import uk.ac.ebi.spot.gwas.deposition.domain.Study;
import uk.ac.ebi.spot.gwas.deposition.dto.StudyDto;

public class StudyDtoAssembler {

    public static StudyDto assemble(Study study) {
        return new StudyDto(study.getStudyTag(),
                study.getGenotypingTechnology(),
                study.getArrayManufacturer(),
                study.getArrayInformation(),
                study.getImputation(),
                study.getVariantCount(),
                study.getStatisticalModel(),
                study.getStudyDescription(),
                study.getTrait(),
                study.getEfoTrait(),
                study.getBackgroundTrait(),
                study.getBackgroundEfoTrait(),
                study.getSummaryStatisticsFile(),
                study.getSummaryStatisticsAssembly());
    }

}
