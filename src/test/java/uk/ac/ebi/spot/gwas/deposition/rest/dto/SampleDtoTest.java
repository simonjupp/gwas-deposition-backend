package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SampleDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SampleDto.class)
                .verify();
    }

}