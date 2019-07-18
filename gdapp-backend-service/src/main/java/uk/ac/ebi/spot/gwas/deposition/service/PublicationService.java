package uk.ac.ebi.spot.gwas.deposition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchPublications;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;

public interface PublicationService {

    Publication retrievePublication(String id, boolean isId);

    FacetedSearchPublications getPublications(Integer page, Integer itemsPerPage, String sortType);

    Page<Publication> getPublications(Pageable page);

}
