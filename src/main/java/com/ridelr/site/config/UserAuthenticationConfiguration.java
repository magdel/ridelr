package com.ridelr.site.config;

import com.ridelr.site.auth.UserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: rfk
 * Date: 16.12.14
 * Time: 3:31
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class UserAuthenticationConfiguration {

    @Bean(initMethod = "init")
    UserAuthenticationProvider userAuthenticationProvider() {
        return new UserAuthenticationProvider();
    }


}
