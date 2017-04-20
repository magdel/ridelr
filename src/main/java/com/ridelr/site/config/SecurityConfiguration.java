package com.ridelr.site.config;

import com.ridelr.site.auth.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Security
 * Created with IntelliJ IDEA.
 * User: rfk
 * Date: 16.07.14
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Bean(name = "ocuAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("t@t").password("t").roles("USER", "ADMIN");

        auth.inMemoryAuthentication()
                .withUser("t@t").password("t").roles("USER", "ADMIN");


    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/adm/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/register*").anonymous()
                .antMatchers("/user/info/**").access("hasRole('ROLE_USER')")
                .antMatchers("/user/infoall").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/events/u*").access("hasRole('ROLE_USER')")
                .antMatchers("/maps/**").access("hasRole('ROLE_USER')")
                .antMatchers("/devices/**").access("hasRole('ROLE_USER')");

        //  HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

        http.formLogin().permitAll().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("email").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout");

        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private AntPathRequestMatcher apiMatcher = new AntPathRequestMatcher("/api/**");

            @Override
            public boolean matches(HttpServletRequest request) {
                // No CSRF due to allowedMethod
                if (allowedMethods.matcher(request.getMethod()).matches()) {
                    return false;
                }

                // No CSRF due to api call
                if (apiMatcher.matches(request)) {
                    return false;
                }

                // CSRF for everything else that is not an API call or an allowedMethod
                return true;
            }
        });
        /*http.csrf().requireCsrfProtectionMatcher(new NegatedRequestMatcher(
                new AntPathRequestMatcher("/api*//**")
         ));*/
        // .and().csrf().csrfTokenRepository(csrfTokenRepository);


        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");

        http.addFilterBefore(filter, CsrfFilter.class);
        http.logout().deleteCookies("JSESSIONID");

        http.exceptionHandling().accessDeniedPage("/errors/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web
                .ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/resources/**")
                .antMatchers("/static/**")
        ;
    }
}