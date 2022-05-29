package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // authentication
        auth.inMemoryAuthentication()
                .withUser("client").password("{noop}client")
                .and()
                .withUser("profile").password("{noop}profilejon")
                .and()
                .withUser("bankjon").password("{noop}bankov");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorithtion
        http.authorizeHttpRequests()
                .antMatchers("/client/**").permitAll()
                .antMatchers("/client/**").hasAnyRole("client")
                .antMatchers("/profile/**").hasAnyRole("profile")
                .anyRequest().authenticated()
                .and().httpBasic();

        http.csrf().disable()
                .cors().disable();
    }
}
