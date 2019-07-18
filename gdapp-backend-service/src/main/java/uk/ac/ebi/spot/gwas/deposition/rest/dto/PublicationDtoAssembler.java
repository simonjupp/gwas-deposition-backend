package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.dto.CorrespondingAuthorDto;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationDto;

public class PublicationDtoAssembler {

    public static PublicationDto assemble(Publication publication) {
        return new PublicationDto(publication.getId(),
                publication.getPmid(),
                publication.getTitle(),
                publication.getJournal(),
                publication.getFirstAuthor(),
                publication.getPublicationDate(),
                new CorrespondingAuthorDto(publication.getCorrespondingAuthor().getAuthorName(),
                        publication.getCorrespondingAuthor().getEmail()),
                publication.getStatus());
    }
}
