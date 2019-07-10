package uk.ac.ebi.spot.gwas.deposition.domain;

import java.util.List;

public class FacetedSearchSubmissions {

    private FacetedMetadata facetedMetadata;

    private List<Submission> submissions;

    public FacetedSearchSubmissions(FacetedMetadata facetedMetadata, List<Submission> submissions) {
        this.facetedMetadata = facetedMetadata;
        this.submissions = submissions;
    }

    public FacetedMetadata getFacetedMetadata() {
        return facetedMetadata;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }
}
