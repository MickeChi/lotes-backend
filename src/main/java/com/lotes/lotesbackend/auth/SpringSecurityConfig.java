package com.lotes.lotesbackend.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
        		.cors(AbstractHttpConfigurer::disable)
        		.csrf(AbstractHttpConfigurer::disable)
        		.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        		.authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET, "/usuario").permitAll()
                                //.requestMatchers(HttpMethod.GET, "/usuario/{id}").hasAnyRole("ADMIN", "USER")
                                //.requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")
                                //.requestMatchers("/usuario/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                //.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                //.addFilter(new JWTValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                
                
                .build();
    }

}
