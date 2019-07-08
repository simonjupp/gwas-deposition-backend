package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PublicationDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(PublicationDto.class)
                .verify();
    }

}