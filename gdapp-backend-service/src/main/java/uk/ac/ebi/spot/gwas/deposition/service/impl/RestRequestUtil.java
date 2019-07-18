package uk.ac.ebi.spot.gwas.deposition.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.ac.ebi.spot.gwas.deposition.util.HttpEntityBuilder;

import javax.servlet.http.HttpServletRequest;

@Component
public class RestRequestUtil {

    @Value("${server.name}")
    private String serverName;

    public HttpEntityBuilder httpEntity() {
        HttpServletRequest currentRequest = null;
        if (RequestContextHolder.getRequestAttributes() != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            currentRequest = attributes.getRequest();
        }

        return new HttpEntityBuilder(currentRequest, serverName);
    }
}
