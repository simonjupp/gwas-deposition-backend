package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedMetadata;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchPublications;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.exception.EntityNotFoundException;
import uk.ac.ebi.spot.gwas.deposition.repository.PublicationRepository;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;
import uk.ac.ebi.spot.gwas.deposition.util.BackendUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private static final Logger log = LoggerFactory.getLogger(PublicationService.class);

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication retrievePublication(String id, boolean isId) {
        log.info("Retrieving publication using id [{}]: {}", isId, id);

        Optional<Publication> optionalPublication = isId ?
                publicationRepository.findById(id) : publicationRepository.findByPmid(id);
        if (!optionalPublication.isPresent()) {
            log.error("Unable to find publication with ID / PMID: {}", id);
            throw new EntityNotFoundException("Unable to find publication with ID / PMID: " + id);
        }

        log.info("Returning publication: {}", optionalPublication.get().getPmid());
        return optionalPublication.get();
    }

    @Override
    public FacetedSearchPublications getPublications(Integer page, Integer itemsPerPage, String sortType) {
        log.info("Retrieving publications: {} - {} - {}", page, itemsPerPage, sortType);

        List<Publication> publications;
        if (page.intValue() == -1 && itemsPerPage == -1) {
            publications = publicationRepository.findAll();
            log.info("Retrieved {} submissions.", publications.size());
        } else {
            Sort.Direction direction = sortType == null ? Sort.Direction.DESC :
                    sortType.equalsIgnoreCase(GWASDepositionBackendConstants.SORT_VALUE_ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page - 1, itemsPerPage, direction, "publicationDate.timestamp");
            Page<Publication> publicationsPage = publicationRepository.findAll(pageable);
            publications = publicationsPage.getContent();
            log.info("Pageable: {}", publications.size());
        }
        log.info("Publications successfully retrieved: {}", publications.size());

        int totalItems = (int) publicationRepository.count();
        int totalPages = (page.intValue() == -1 && itemsPerPage == -1) ? 1 : BackendUtil.computeTotalPages(totalItems, itemsPerPage);
        FacetedMetadata facetedMetadata = new FacetedMetadata(page, itemsPerPage,
                totalPages, totalItems, new HashMap<>());
        log.info("Found {} total publications with this criteria.", totalItems);
        return new FacetedSearchPublications(facetedMetadata, publications);
    }

    @Override
    public Page<Publication> getPublications(Pageable page) {
        log.info("Retrieving publications: {} - {} - {}", page.getPageNumber(), page.getPageSize(), page.getSort().toString());
        return publicationRepository.findAll(page);
    }
}
