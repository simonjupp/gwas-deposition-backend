package uk.ac.ebi.spot.gwas.deposition.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class FileUploadDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(FileUploadDto.class)
                .verify();
    }

}