package com.trungtamjava.tiktok;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserDetailsService userdetailsService;
	 

	    @Autowired
	    public void config(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.userDetailsService(userdetailsService)
	                .passwordEncoder(new BCryptPasswordEncoder());
	    }
	
	    @Autowired
	    JwtTokenFilter filter;
	    
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) 
	    		throws Exception {
	    	return configuration.getAuthenticationManager();
	    }
	    //@CrossOrigin(origins = "http://localhost:3000")
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	   @Bean
	    public SecurityFilterChain config(HttpSecurity http) throws Exception {
		   http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**"))).
		   authorizeHttpRequests(authorize ->
		   authorize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
		   			.requestMatchers("/user/**").authenticated()
		   			.anyRequest().permitAll()
				   ).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
		   .httpBasic(httpBasic -> httpBasic.hashCode());
		  http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		   return http.build();
	   }
}
