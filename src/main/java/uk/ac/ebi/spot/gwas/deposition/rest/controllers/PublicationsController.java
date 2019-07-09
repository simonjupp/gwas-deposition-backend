package uk.ac.ebi.spot.gwas.deposition.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.gwas.deposition.constants.GWASDepositionBackendConstants;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.PublicationDto;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly.PublicationDtoAssembler;
import uk.ac.ebi.spot.gwas.deposition.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = GWASDepositionBackendConstants.API_V1 + GWASDepositionBackendConstants.API_PUBLICATIONS)
public class PublicationsController {

    private static final Logger log = LoggerFactory.getLogger(PublicationsController.class);

    @Autowired
    private PublicationService publicationService;

    /**
     * GET /v1/publications/{publicationId}
     */
    @GetMapping(value = "/{publicationId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PublicationDto getPublication(@PathVariable String publicationId, HttpServletRequest request) {
        log.info("Request to get publication: {}.", publicationId);

        Publication publication = publicationService.retrievePublication(publicationId, true);
        log.info("Returning publication: {}", publication.getPmid());
        return PublicationDtoAssembler.assemble(publication);
    }

    /**
     * GET /v1/publications?pmid=<pmid>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PublicationDto> getPublications(HttpServletRequest request,
                                                @RequestParam(value = GWASDepositionBackendConstants.PARAM_PMID,
                                                        required = false)
                                                        String pmid) {
        log.info("Request to get publications [{}].", pmid);
        if (pmid != null) {
            Publication publication = publicationService.retrievePublication(pmid, false);
            log.info("Returning publication: {}", publication.getPmid());
            return Arrays.asList(new PublicationDto[]{PublicationDtoAssembler.assemble(publication)});
        } else {
            List<Publication> publications = publicationService.retrievePublications(pmid);
            log.info("Returning {} publications.", publications.size());
            List<PublicationDto> publicationDtos = publications.stream().map(PublicationDtoAssembler::assemble)
                    .collect(Collectors.toList());
            return publicationDtos;
        }
    }
}
