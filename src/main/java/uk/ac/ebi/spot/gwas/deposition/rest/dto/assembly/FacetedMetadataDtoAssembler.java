package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.FacetedMetadata;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.FacetedMetadataDto;

public class FacetedMetadataDtoAssembler {

    public static FacetedMetadataDto assemble(FacetedMetadata facetedMetadata) {
        return new FacetedMetadataDto(facetedMetadata.getPage(),
                facetedMetadata.getItemsPerPage(),
                facetedMetadata.getTotalPages(),
                facetedMetadata.getTotalItems(),
                facetedMetadata.getFilters());
    }
}
