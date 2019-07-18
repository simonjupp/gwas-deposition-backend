package uk.ac.ebi.spot.gwas.deposition.dto.templateschema;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class SummaryStatsSchemaDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SummaryStatsSchemaDto.class)
                .verify();
    }

}