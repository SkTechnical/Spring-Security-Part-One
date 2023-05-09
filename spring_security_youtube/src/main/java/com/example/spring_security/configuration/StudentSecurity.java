package com.example.spring_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class StudentSecurity {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService detailsService() {
		UserDetails customer=User.withUsername("sk").password(passwordEncoder().encode("sk")).roles("customer_user").build();
		UserDetails admin=User.withUsername("ad").password(passwordEncoder().encode("ad")).roles("admin_user").build();
        InMemoryUserDetailsManager detailsManage=new  InMemoryUserDetailsManager(customer,admin);
		return detailsManage;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/customer").hasRole("customer_user")
		.requestMatchers("admin").hasRole("admin_user")
		.requestMatchers("/public")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		return http.build();
	}

}
