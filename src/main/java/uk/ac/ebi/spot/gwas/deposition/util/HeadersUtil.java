package uk.ac.ebi.spot.gwas.deposition.util;

import uk.ac.ebi.spot.gwas.deposition.constants.IDPConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HeadersUtil {

    public static String extractJWT(HttpServletRequest httpServletRequest) {
        String jwt = httpServletRequest.getHeader(IDPConstants.JWT_TOKEN);
        if (jwt == null && httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(IDPConstants.COOKIE_ACCESSTOKEN)) {
                    jwt = cookie.getValue();
                    break;
                }
                if (cookie.getName().equalsIgnoreCase(IDPConstants.JWT_TOKEN)) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        return jwt;
    }}
