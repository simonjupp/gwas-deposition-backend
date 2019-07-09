package uk.ac.ebi.spot.gwas.deposition.service;

import uk.ac.ebi.spot.gwas.deposition.domain.Publication;

import java.util.List;

public interface PublicationService {

    Publication retrievePublication(String id, boolean isId);

    List<Publication> retrievePublications(String pmid);
}
