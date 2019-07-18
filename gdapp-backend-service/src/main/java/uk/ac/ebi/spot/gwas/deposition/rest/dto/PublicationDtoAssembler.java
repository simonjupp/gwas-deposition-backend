package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.dto.CorrespondingAuthorDto;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.controllers.PublicationsController;

@Component
public class PublicationDtoAssembler  implements ResourceAssembler<Publication, Resource<PublicationDto>> {

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

    public Resource<PublicationDto> toResource(Publication publication) {

        PublicationDto publicationDto = new PublicationDto(publication.getId(),
                publication.getPmid(),
                publication.getTitle(),
                publication.getJournal(),
                publication.getFirstAuthor(),
                publication.getPublicationDate(),
                new CorrespondingAuthorDto(publication.getCorrespondingAuthor().getAuthorName(),
                        publication.getCorrespondingAuthor().getEmail()),
                publication.getStatus());


        final ControllerLinkBuilder lb = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(PublicationsController.class).getPublication(publication.getId(), false, null));

        Resource<PublicationDto> resource = new Resource<PublicationDto>(publicationDto);
        resource.add(lb.withSelfRel());
        return resource;

    }
}
