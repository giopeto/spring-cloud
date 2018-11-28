package com.edge;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Order(99)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    /*@Bean
    public OAuth2RestOperations restOperations(
            OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           /* .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))*/
            .and()
            .authorizeRequests()
                .antMatchers("/uaa/**").permitAll()
                .antMatchers("/uaa/oauth/token").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/store/home").permitAll()
                .antMatchers("/store/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;
    }
}
