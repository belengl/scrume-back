package com.spring.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.Security.UserAccountService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomBasicAuthenticationPoint customBasicAuthenticationEntryPoint;
	private final UserAccountService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/profile/list").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/profile/get/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/profile/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/profile/delete/**").hasRole("ADMIN")
				.antMatchers("/team/*").authenticated()
				.antMatchers(HttpMethod.GET, "/api/login/roles").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/project/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/project/update").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/project/delete").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/project/list").authenticated();
		http.logout().logoutUrl("/api/login/logout").clearAuthentication(true).deleteCookies("JSESSIONID").and().csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
