package uk.ac.ebi.spot.gwas.deposition.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class FacetedMetadataTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(FacetedMetadata.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .verify();
    }

}