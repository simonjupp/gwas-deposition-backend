package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.spot.gwas.deposition.config.RestInteractionConfig;

@Service
public class GatewayService {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected RestInteractionConfig restInteractionConfig;

    @Autowired
    protected RestRequestUtil restRequestUtil;
}
