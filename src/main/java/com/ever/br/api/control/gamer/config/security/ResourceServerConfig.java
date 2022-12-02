package com.ever.br.api.control.gamer.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/users","/users/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.DELETE,"/users","/users/**").hasAnyRole("ADMIN")
                .antMatchers("/players","/players/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/users","/users/**").permitAll()
                .anyRequest().authenticated();
//               .anyRequest().permitAll();
    }
}
