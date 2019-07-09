package uk.ac.ebi.spot.gwas.deposition.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import uk.ac.ebi.spot.gwas.deposition.config.GWASDepositionBackendConfig;
import uk.ac.ebi.spot.gwas.deposition.util.HeadersUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private GWASDepositionBackendConfig gwasDepositionBackendConfig;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) {
        if (!"/error".equals(httpServletRequest.getRequestURI())) {
            if (!gwasDepositionBackendConfig.isAuthEnabled()) {
                return true;
            }

            String jwt = HeadersUtil.extractJWT(httpServletRequest);
            //TODO: Handle JWT
        }

        return true;
    }
}
