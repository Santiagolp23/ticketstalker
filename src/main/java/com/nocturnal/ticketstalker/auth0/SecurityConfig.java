package com.nocturnal.ticketstalker.auth0;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final LogoutHandler logoutHandler;


    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow all users to access the home pages and the static images directory
                .mvcMatchers("/static/bootstrap/assets/img/**").permitAll()
                // all other requests must be authenticated
                .mvcMatchers("/tickets").hasAuthority("SCOPE_MANAGER")
                .anyRequest().authenticated()
                .and().oauth2Login()
                .and().logout()
                // handle logout requests at /logout path
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // customize logout handler to log out of Auth0
                .addLogoutHandler(logoutHandler);
        return http.build();
    }

}

