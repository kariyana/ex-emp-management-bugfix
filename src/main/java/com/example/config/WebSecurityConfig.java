package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.constant.UrlConst;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
		
	private final CustomAuthenticationProvider customAuthenticationProvider;
	private final PasswordEncoder passwordEncoder;
	/**
	 * パスワードをhash化
	 * @return hashPassword
	 */
	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// 	System.out.println("passwordEncoder");
	// 	return new BCryptPasswordEncoder();
	// }

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// System.out.println("securityChain");
		http
		 // カスタム認証プロバイダを設定
		.authenticationProvider(customAuthenticationProvider)
		.authorizeHttpRequests(authz -> authz
			.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
			.anyRequest().authenticated()
		)
		.formLogin(login -> login 
        .loginPage("/login") 
		.usernameParameter("mailAddress")
        .loginProcessingUrl("/login") 
        .defaultSuccessUrl("/employees")
		// )
        // // .logout(logout -> logout
        // //     .permitAll()
        )
		;

	return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsManager(){
		System.out.println("UserDetailService");
		UserDetails admin = User.builder()
								.username("test@gmail.com")
								.password(passwordEncoder.encode("testtest"))
								.authorities("ADMIN")
								.build();
			
		UserDetails user = User.builder()
								.username("test@hash.com")
								.password(passwordEncoder.encode("testtest"))
								.authorities("USER")
								.build();
		return new InMemoryUserDetailsManager(admin,user);
			
	}

}
