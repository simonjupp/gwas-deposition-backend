package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.CorrespondingAuthorDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.PublicationDto;

public class PublicationDtoAssembler {

    public static PublicationDto assemble(Publication publication) {
        return new PublicationDto(publication.getPmid(),
                publication.getTitle(),
                publication.getJournal(),
                publication.getAuthors(),
                publication.getPublicationDate(),
                new CorrespondingAuthorDto(publication.getCorrespondingAuthor().getAuthorName(),
                        publication.getCorrespondingAuthor().getEmail()),
                publication.getStatus());
    }
}
