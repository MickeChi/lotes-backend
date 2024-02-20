package com.lotes.lotesbackend.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors(AbstractHttpConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable)
		/*.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET, "/usuario").permitAll()
				.requestMatchers(HttpMethod.GET, "/proyecto").permitAll()
				.anyRequest().authenticated()
				)*/
		.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
		.sessionManagement((sm) -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

}
