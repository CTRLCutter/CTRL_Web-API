package com.ctrlcutter.api.ctrl_webapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO Entfernen und richtig machen mit authentication!! Only for development!!
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
