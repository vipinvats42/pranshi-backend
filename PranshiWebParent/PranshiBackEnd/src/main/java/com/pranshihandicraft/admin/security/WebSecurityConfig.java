package com.pranshihandicraft.admin.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/*
	 * private final UserDetailsService userDetailsService; private final JwtFilter
	 * jwtFilter;
	 * 
	 * public WebSecurityConfig(@Lazy UserDetailsService userDetailsService,
	 * JwtFilter jwtFilter) { this.userDetailsService = userDetailsService;
	 * this.jwtFilter = jwtFilter; }
	 * 
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { return http.csrf(csrf -> csrf.disable())
	 * .authorizeHttpRequests(auth -> auth .requestMatchers("PranshiAdmin",
	 * "new").permitAll() .anyRequest().authenticated()) .sessionManagement(session
	 * -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	 * .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	 * .build(); }
	 * 
	 * 
	 * 
	 * @Bean public AuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	 * provider.setUserDetailsService(userDetailsService); return provider; }
	 */
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
	
	 @Bean 
	 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	  throws Exception { 
		
		  				
		 httpSecurity.authorizeRequests().anyRequest().permitAll();	 
		 
		 return httpSecurity.build();
	 }
	 
	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	 
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	
}