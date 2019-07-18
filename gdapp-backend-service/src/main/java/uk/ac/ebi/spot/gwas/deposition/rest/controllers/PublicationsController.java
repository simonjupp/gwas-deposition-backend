package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<Resource<PublicationDto>> getPublication(@PathVariable String publicationId,
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
        return new ResponseEntity<>( publicationDtoAssembler.toResource(publication), HttpStatus.OK);

    }



    @Autowired PublicationDtoAssembler publicationDtoAssembler;


    /**
     * GET /v1/publications
     */
    @GetMapping(produces = "application/hal+json")
    public HttpEntity<PagedResources<PublicationDto>> getPublications(
            @PageableDefault(size = 20, page = 0) Pageable pageable,
            PagedResourcesAssembler assembler) {
        log.info("Request to retrieve publications: {} - {} - {}", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        Page<Publication> facetedSearchPublications = publicationService.getPublications(pageable);
        return new ResponseEntity<>( assembler.toResource(facetedSearchPublications, publicationDtoAssembler), HttpStatus.OK);

    }
}
