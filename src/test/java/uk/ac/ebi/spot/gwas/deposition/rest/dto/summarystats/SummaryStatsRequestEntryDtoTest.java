package uk.ac.ebi.spot.gwas.deposition.rest.dto.summarystats;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SummaryStatsRequestEntryDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SummaryStatsRequestEntryDto.class)
                .verify();
    }

}