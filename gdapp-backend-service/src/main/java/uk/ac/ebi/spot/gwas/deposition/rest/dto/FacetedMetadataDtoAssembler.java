package uk.ac.ebi.spot.gwas.deposition.rest.dto;


import uk.ac.ebi.spot.gwas.deposition.domain.FacetedMetadata;
import uk.ac.ebi.spot.gwas.deposition.dto.FacetedMetadataDto;

public class FacetedMetadataDtoAssembler {

    public static FacetedMetadataDto assemble(FacetedMetadata facetedMetadata) {
        return new FacetedMetadataDto(facetedMetadata.getPage(),
                facetedMetadata.getItemsPerPage(),
                facetedMetadata.getTotalPages(),
                facetedMetadata.getTotalItems(),
                facetedMetadata.getFilters());
    }
}
