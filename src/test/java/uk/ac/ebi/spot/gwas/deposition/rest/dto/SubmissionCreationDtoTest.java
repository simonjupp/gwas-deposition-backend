package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SubmissionCreationDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SubmissionCreationDto.class)
                .verify();
    }

}