package uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SummaryStatsRequestDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SummaryStatsRequestDto.class)
                .verify();
    }

}