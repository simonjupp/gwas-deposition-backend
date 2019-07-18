package uk.ac.ebi.spot.gwas.deposition.dto.templateschema;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class TemplateColumnDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(TemplateColumnDto.class)
                .verify();
    }

}