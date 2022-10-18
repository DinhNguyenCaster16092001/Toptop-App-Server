package com.cp2196g03g2.server.toptop;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cp2196g03g2.server.toptop.service.IEmailService;

@SpringBootApplication
public class ToptopApplication {

	@Autowired
	private IEmailService emailService;
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ToptopApplication.class, args);
	}
	
	/*
	 * @EventListener(ApplicationReadyEvent.class) public void sendMail() {
	 * emailService.sendMail("ndhdinha19059@cusc.ctu.edu.vn", "Hello", "Lmao"); }
	 */

}
