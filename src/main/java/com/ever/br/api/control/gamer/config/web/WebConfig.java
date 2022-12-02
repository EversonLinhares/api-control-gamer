package com.ever.br.api.control.gamer.config.web;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
        List<String> all = Arrays.asList("*");

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(all);
        corsConfiguration.setAllowedHeaders(all);
        corsConfiguration.setAllowedMethods(all);
//      quando se trata de usar um coringa * isso não pode ser setado como true
//      corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }
}
