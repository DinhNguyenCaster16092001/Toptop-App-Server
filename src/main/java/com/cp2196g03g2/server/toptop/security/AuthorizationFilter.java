package com.cp2196g03g2.server.toptop.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthorizationFilter extends OncePerRequestFilter {

	private String[] URL = {"/index.html",
							"/api/v1/login", 
							"/api/v1/account",
							"/api/v1/video/watch",
							"/api/v1/comment/video"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		for (String url : URL) {
			if(request.getServletPath().contains(url)) {
				filterChain.doFilter(request, response);
				break;
			}else {
				String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
				if (authorizationHeader != null & authorizationHeader.startsWith("Bearer ")) {
					try {
						String token = authorizationHeader.substring("Bearer ".length());
						Algorithm algorithm = Algorithm.HMAC256("%hDWZP9zs7Upjs7$cZI#ZwKP8IW69$".getBytes());
						JWTVerifier verifier = JWT.require(algorithm).build();
						DecodedJWT decodedJWT = verifier.verify(token);
						String username = decodedJWT.getSubject();
						String[] roles = decodedJWT.getClaim("role").asArray(String.class);
						Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
						Arrays.stream(roles).forEach(role -> {
							authorities.add(new SimpleGrantedAuthority(role));
						});
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								username, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						filterChain.doFilter(request, response);
						break;
					} catch (Exception e) {
						response.setHeader("error", e.getMessage());
						response.setStatus(HttpStatus.FORBIDDEN.value());
						Map<String, String> error = new HashMap<>();
						error.put("error_message", e.getMessage());
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						new ObjectMapper().writeValue(response.getOutputStream(), error);
						break;
					}
				} else {
					filterChain.doFilter(request, response);
					break;
				}
			}
		}
	}
}
