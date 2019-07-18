package uk.ac.ebi.spot.gwas.deposition.domain;

import java.util.List;

public class FacetedSearchPublications {

    private FacetedMetadata facetedMetadata;

    private List<Publication> publications;

    public FacetedSearchPublications(FacetedMetadata facetedMetadata, List<Publication> publications) {
        this.facetedMetadata = facetedMetadata;
        this.publications = publications;
    }

    public FacetedMetadata getFacetedMetadata() {
        return facetedMetadata;
    }

    public List<Publication> getPublications() {
        return publications;
    }
}
