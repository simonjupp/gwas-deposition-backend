package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats.SummaryStatsResponseDto;

public class SummaryStatsResponseDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SummaryStatsResponseDto.class)
                .verify();
    }

}