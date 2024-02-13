package com.example.config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http
		// 	.authorizeHttpRequests((requests) -> requests
		// 		.requestMatchers("/login", "/").permitAll()
		// 		.anyRequest().authenticated()
		// 	)
		// 	.formLogin((form) -> form
		// 		.loginPage("/login")
		// 		.permitAll()
		// 	)
		// 	.logout((logout) -> logout.permitAll());

		return http.build();
	}

    // @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		 User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();
    //     System.out.println(user);
	// 	return new InMemoryUserDetailsManager(user);
	// }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Autowired
    
    // void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService)
    //         .passwordEncoder(passwordEncoder());
    // }
}
