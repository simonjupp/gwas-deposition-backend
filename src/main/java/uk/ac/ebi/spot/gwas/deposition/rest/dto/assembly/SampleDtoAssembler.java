package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.Sample;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.SampleDto;

public class SampleDtoAssembler {

    public static SampleDto assemble(Sample sample) {
        return new SampleDto(sample.getStudyTag(),
                sample.getStage(),
                sample.getSize(),
                sample.getCases(),
                sample.getControls(),
                sample.getSampleDescription(),
                sample.getAncestryCategory(),
                sample.getAncestry(),
                sample.getAncestryDescription(),
                sample.getCountryRecruitement());
    }
}
