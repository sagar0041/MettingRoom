package com.meetingroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meetingroom.jwt.JwtAuthorizationFilter;
import com.meetingroom.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cross-origin-resource-sharing
		http.cors().and().authorizeRequests()
				// These are public pages.
				.antMatchers("/resources/**", "/error", "/api/user/**").permitAll()
				// These can be reachable for just have admin role.
				.antMatchers("/api/admin/**").hasRole("ADMIN")
				// These can be reachable for just have pm role.
				.antMatchers("/api/pm/**").hasRole("PM")
				// These can be reachable for just have tl role.
				.antMatchers("/api/tl/**").hasRole("TL")
				// all remaining paths should need Authentication.
				.anyRequest().fullyAuthenticated().and()
				// logout will logout the user out by invalidate session.
				.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST")).and()
				// login form and path
				.formLogin().loginPage("/api/user/login").and()
				// enable basic authentication.Http Header: basic username:password
				.httpBasic().and()
				// Cross site request forgery
				.csrf().disable();
	
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenProvider));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	//Cross Origin resource sharing
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
			
		};
		
	}
}
