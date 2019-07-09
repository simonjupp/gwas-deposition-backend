package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.exception.EntityNotFoundException;
import uk.ac.ebi.spot.gwas.deposition.repository.PublicationRepository;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;

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
}
