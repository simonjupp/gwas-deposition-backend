package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchPublications;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;

public interface PublicationService {

    Publication retrievePublication(String id, boolean isId);

    FacetedSearchPublications getPublications(Integer page, Integer itemsPerPage, String sortType);
}
