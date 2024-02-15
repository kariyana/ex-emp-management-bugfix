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
	 * ログイン認証設定
	 * @param http
	 * @return
	 * @throws Exception
	 */
    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http
		//  // カスタム認証プロバイダを設定
		// .authenticationProvider(customAuthenticationProvider)
		
		// .authorizeHttpRequests(authz -> authz
		// 	.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
		// 	.anyRequest().authenticated()
		// )
		// .formLogin(login -> login 
		// // .loginProcessingUrl("/login")
        // .loginPage("/login") 
		// .usernameParameter("mailAddress")
        // .defaultSuccessUrl("/employees")
        // .failureUrl("/login?error") 
        // .permitAll() 
		// // )
        // // // .logout(logout -> logout
        // // //     .permitAll()
        // )
		// ;

	return http.build();
	}
	
}
