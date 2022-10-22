package com.cp2196g03g2.server.toptop.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cp2196g03g2.server.toptop.dto.UsernameAndPasswordAuthenticationRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	private AuthenticationManager authenticationManager;
	
	private IUserService userService;

	

	public AuthenticationFilter(AuthenticationManager authenticationManager, IUserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
					.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

			logger.info("Email is " + authenticationRequest.getUsername());
			logger.info("Password is " + authenticationRequest.getPassword());

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword());
			return authenticationManager.authenticate(authenticationToken);

		} catch (IOException e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		Calendar calendarAccessToken = Calendar.getInstance();
		Date nowAccessToken = calendarAccessToken.getTime();
		calendarAccessToken.add(Calendar.YEAR, 1);
		Date accessToken_expireDate = calendarAccessToken.getTime();

		Calendar calendarRefreshToken = Calendar.getInstance();
		Date nowRefreshToken = calendarRefreshToken.getTime();
		calendarRefreshToken.add(Calendar.YEAR, 5);
		Date refreshToken_expireDate = calendarRefreshToken.getTime();

		User user = (User) authResult.getPrincipal();
		String idUser = userService.findByEmail(user.getUsername()).getId();
		Algorithm algorithm = Algorithm.HMAC256("%hDWZP9zs7Upjs7$cZI#ZwKP8IW69$".getBytes());
		String access_token = JWT.create().withSubject(user.getUsername()).withExpiresAt(accessToken_expireDate)
				.withIssuedAt(nowAccessToken).withIssuer(request.getRequestURL().toString())
				.withClaim("role",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.withClaim("id", idUser)
				.sign(algorithm);

		String refresh_token = JWT.create().withSubject(user.getUsername()).withExpiresAt(refreshToken_expireDate)
				.withIssuedAt(nowRefreshToken).withIssuer(request.getRequestURL().toString()).sign(algorithm);

		response.setHeader("access_token", access_token);
		response.setHeader("refresh_token", refresh_token);
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refesh_token", refresh_token);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	}

}
