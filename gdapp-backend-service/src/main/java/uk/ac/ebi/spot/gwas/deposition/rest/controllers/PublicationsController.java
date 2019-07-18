package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.FacetedSearchPublications;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationDto;
import uk.ac.ebi.spot.gwas.deposition.dto.PublicationsResultDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.FacetedMetadataDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.PublicationDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = GWASDepositionBackendConstants.API_V1 + GWASDepositionBackendConstants.API_PUBLICATIONS)
public class PublicationsController {

    private static final Logger log = LoggerFactory.getLogger(PublicationsController.class);

    @Autowired
    private PublicationService publicationService;

    /**
     * GET /v1/publications/{publicationId}?pmid=true | false
     */
    @GetMapping(value = "/{publicationId}",
            produces = "application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public PublicationDto getPublication(@PathVariable String publicationId,
                                                   @RequestParam(value = GWASDepositionBackendConstants.PARAM_PMID,
                                                           required = false)
                                                           Boolean pmid,
                                                   HttpServletRequest request) {
        log.info("Request to get publication: {} - {}.", publicationId, pmid);
        Publication publication;
        if (pmid != null) {
            publication = publicationService.retrievePublication(publicationId, !pmid.booleanValue());
        } else {
            publication = publicationService.retrievePublication(publicationId, true);
        }
        log.info("Returning publication: {}", publication.getPmid());
        PublicationDto publicationDto = PublicationDtoAssembler.assemble(publication);
        publicationDto.add(linkTo(methodOn(PublicationsController.class).getPublication(publicationId, pmid, request)).withSelfRel());
        return publicationDto;
    }

    /**
     * GET /v1/publications
     */
    @GetMapping(produces = "application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public PublicationsResultDto getPublications(HttpServletRequest request,
                                                 @RequestParam(
                                                         value = GWASDepositionBackendConstants.PARAM_PAGE,
                                                         required = false,
                                                         defaultValue = "1")
                                                         Integer page,
                                                 @RequestParam(
                                                         value = GWASDepositionBackendConstants.PARAM_NO_ITEMS,
                                                         required = false,
                                                         defaultValue = "10")
                                                         Integer itemsPerPage,
                                                 @RequestParam(
                                                         value = GWASDepositionBackendConstants.PARAM_SORT,
                                                         required = false)
                                                         String sortType) {
        log.info("Request to retrieve publications: {} - {} - {}", page, itemsPerPage, sortType);
        FacetedSearchPublications facetedSearchPublications = publicationService.getPublications(page, itemsPerPage, sortType);
        log.info("Returning {} publications.", facetedSearchPublications.getPublications().size());
        List<PublicationDto> publicationDtos = new ArrayList<>();
        for (Publication publication : facetedSearchPublications.getPublications()) {
            Link selfLink = linkTo(PublicationsController.class).slash(publication.getId()).withSelfRel();
            PublicationDto publicationDto = PublicationDtoAssembler.assemble(publication);
            publicationDto.add(selfLink);
            publicationDtos.add(publicationDto);
        }
        Link link = linkTo(PublicationsController.class).withSelfRel();
        PublicationsResultDto publicationsResultDto = new PublicationsResultDto(FacetedMetadataDtoAssembler.assemble(facetedSearchPublications.getFacetedMetadata()), publicationDtos);
        publicationsResultDto.add(link);
        return publicationsResultDto;
    }
}
