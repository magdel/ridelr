package com.ridelr.site.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * SiteUser: rfk
 * Date: 15.09.14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */

public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    //private final OUserRepository oUserRepository;
    private final PasswordEncoder passwordEncoder;


    public UserAuthenticationProvider() {
        this.passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom(("" + System.currentTimeMillis()).getBytes()));
        //setUserCache(); use for speed up
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!siteUserStat.getUsername().equals(username)) {
            throw new UsernameNotFoundException("Unable to auth: " + username);
        }
        return siteUserStat;
        /*UserDocument byUsername = oUserRepository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("Unable to auth: " + username);
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (String auth : byUsername.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        return new SiteUser(byUsername.getId(), byUsername.getUsername(), byUsername.getPassword(), byUsername.isEnabled(), byUsername.isAccountNonExpired(), byUsername.isCredentialsNonExpired(), byUsername.isAccountNonLocked(), authorities,
                byUsername.getCreated());*/
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public SiteUser getUser(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException("Not authed " + authentication.getCredentials().toString());
        }
        return (SiteUser) authentication.getPrincipal();
    }

    private static SiteUser siteUserStat;

    void init() {
        siteUserStat = new SiteUser("-1", "t@t", encodePassword("t"),
                new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN"))), new Date());
    }
}
