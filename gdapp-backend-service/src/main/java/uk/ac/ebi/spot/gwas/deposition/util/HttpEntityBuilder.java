package uk.ac.ebi.spot.gwas.deposition.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HttpEntityBuilder {

    private static final String X_REQUEST_ID = "X-Request-ID";

    private static final String JWT = "jwt";

    private HttpServletRequest currentRequest;

    private String xRequestId;

    private String jwt;

    private Object body;

    private MediaType contentType;

    private HttpHeaders headers;

    public HttpEntityBuilder(HttpServletRequest currentRequest, String serverName) {
        this.currentRequest = currentRequest;

        // we will always process and include the X-Request-ID
        String currentXRequestId = currentRequest != null ? currentRequest.getHeader(X_REQUEST_ID) : null;
        String newXRequestId = currentXRequestId != null ?
                currentXRequestId + ", " + serverName + "/-" :
                serverName + "/-";

        this.xRequestId = newXRequestId;
        this.headers = new HttpHeaders();
    }

    public String extractJWT() {
        String jwt = currentRequest.getHeader(JWT);
        if (jwt == null) {
            if (currentRequest.getCookies() != null) {
                for (Cookie cookie : currentRequest.getCookies()) {
                    if (cookie.getName().equalsIgnoreCase(JWT)) {
                        jwt = cookie.getValue();
                        break;
                    }
                    if (cookie.getName().equalsIgnoreCase(JWT)) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return jwt;
    }

    /**
     * Sets a 'jwt' header using the same header on the current request
     */
    public HttpEntityBuilder addCurrentJwt() {
        this.jwt = currentRequest != null ? extractJWT() : null;
        return this;
    }

    public HttpEntityBuilder withJwtHeader(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public HttpEntityBuilder withHeaders(Map<String, String> headerMap) {

        headerMap.entrySet().stream()
                .forEach(entry -> headers.set(entry.getKey(), entry.getValue()));
        return this;
    }

    public HttpEntityBuilder withJsonBody(Object body) {
        this.body = body;
        this.contentType = MediaType.APPLICATION_JSON;
        return this;
    }

    public HttpEntityBuilder withStringBody(String body) {
        this.body = body;
        this.contentType = MediaType.TEXT_PLAIN;
        return this;
    }

    public HttpEntityBuilder withMultipartBody(Object body) {
        this.body = body;
        this.contentType = MediaType.MULTIPART_FORM_DATA;
        return this;
    }

    public HttpEntity build() {

        if (jwt != null) {
            headers.set(JWT, jwt);
        }

        if (xRequestId != null) {
            headers.set(X_REQUEST_ID, xRequestId);
        }

        if (body != null) {
            headers.setContentType(contentType);
        }

        return new HttpEntity(body, headers);
    }
}
