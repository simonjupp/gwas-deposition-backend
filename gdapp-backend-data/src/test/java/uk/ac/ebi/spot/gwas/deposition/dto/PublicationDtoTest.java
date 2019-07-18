package uk.ac.ebi.spot.gwas.deposition.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class PublicationDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(PublicationDto.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .withRedefinedSuperclass()
                .withIgnoredFields("links")
                .verify();
    }

}