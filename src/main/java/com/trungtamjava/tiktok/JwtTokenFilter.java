package com.trungtamjava.tiktok;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trungtamjava.tiktok.service.impl.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter  {
	@Autowired
	JwtTokenService jwtTokenService;
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken !=null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.substring(7);
			String userName = jwtTokenService.getUserName(token);
			if (userName!=null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				
				Authentication authentication = 
						new UsernamePasswordAuthenticationToken(
								userDetails,"",userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
				
		}
		filterChain.doFilter(request, response);
	}

}
