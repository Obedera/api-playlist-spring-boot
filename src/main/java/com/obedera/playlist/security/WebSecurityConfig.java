package com.obedera.playlist.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilterAfter(new JWTFiltro(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/api/v1/doc/**").permitAll()
			.antMatchers("/v2/**").permitAll()
			.antMatchers("/api/v1/usuario/login").permitAll()
			.anyRequest().authenticated();
	}
}