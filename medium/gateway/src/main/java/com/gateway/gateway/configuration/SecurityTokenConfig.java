package com.gateway.gateway.configuration;

import javax.servlet.http.HttpServletResponse;

import com.gateway.gateway.filters.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


//@EnableWebSecurity 	// Enable security configuration. This annotation denotes configuration for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHENTICATION_PREFIX = "authentication";
    public static final String AUTHENTICATION_VERSION = "V1";
    public static final String AUTHENTICATION_BASE_URL = AUTHENTICATION_PREFIX + "/" + AUTHENTICATION_VERSION;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                // authorization requests configuration
                .authorizeRequests()
                // allow all who are accessing "auth" service
                .antMatchers(POST, jwtConfig.getUri()).permitAll()
                // must be an admin if trying to access admin area (authentication is also required here)
                .antMatchers("/users/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/book-service" + "/1/**").hasRole("ADMIN")
                // Any other request must be authenticated
                .anyRequest().authenticated();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}