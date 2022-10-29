package com.cp2196g03g2.server.toptop.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.cp2196g03g2.server.toptop.security.AuthenticationFilter;
import com.cp2196g03g2.server.toptop.security.AuthorizationFilter;
import com.cp2196g03g2.server.toptop.service.IUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean(), userService);
		authenticationFilter.setFilterProcessesUrl("/api/v1/login");
		
		CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
		corsConfig.addAllowedMethod(HttpMethod.DELETE);
		corsConfig.addAllowedMethod(HttpMethod.PUT);
		http.csrf().disable();
		http.cors().configurationSource(request -> corsConfig);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().authenticated();
		/*
		 * http.authorizeRequests().antMatchers("/api/v1/account/**").permitAll().
		 * antMatchers("/api/v1/video/watch/**")
		 * .permitAll().antMatchers("/api/v1/video/interact/**").authenticated().
		 * antMatchers("/api/v1/profile/**")
		 * .authenticated().antMatchers("/api/v1/management/coupon/**").hasAnyAuthority(
		 * "ROLE_COUPON_MODERATOR")
		 * .antMatchers("/api/v1/management/ticketshop/**").hasAnyAuthority(
		 * "ROLE_TICKET_MODERATOR")
		 * .antMatchers("/api/v1/management/user/**").hasAnyAuthority("ROLE_SUPERADMIN")
		 * .antMatchers("/api/v1/management/profile/**").authenticated().anyRequest().
		 * authenticated();
		 */
		http.addFilter(authenticationFilter);
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/api/v1/comment/video/**");
		web.ignoring().antMatchers("/api/v1/video/watch/**");
		web.ignoring().antMatchers("/api/v1/account/**");
		web.ignoring().antMatchers("/api/v1/hashtag/**");
		web.ignoring().antMatchers("/api/v1/friendship/**");
		web.ignoring().antMatchers("/chat/**");
	}

}
