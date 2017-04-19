package com.ridelr.site.web.controller;

import com.ridelr.site.web.auth.AuthenticationFacade;
import com.ridelr.site.web.controller.exception.NotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthenticatedException("No authentication"); //throw exception to prevent access and find places to redirect on login page
        }
        return authentication;
    }

  /*  @Override
    public SiteUser getUser() {
        return (SiteUser) getAuthentication().getPrincipal();
    }
*/
    @Override
    public String getUserId() {
        return "id";//return getUser().getId();
    }
}
