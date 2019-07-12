package uk.ac.ebi.spot.gwas.deposition.rest.dto.templateschema;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class TemplateSchemaDtoTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(TemplateSchemaDto.class)
                .verify();
    }

}