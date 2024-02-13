package com.example.config;

import javax.sql.DataSource;

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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	private final DataSource dataSource;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.formLogin(login -> login // 2
		// .usernameParameter("mailAddress")
        // .passwordParameter("password")
        // .loginProcessingUrl("/login") // 3
        .loginPage("/login") // 4
        // .successForwardUrl("/employee/showList") // 5
        // .failureUrl("/login?failed") // 6 
        .permitAll() // 7
		)
		.authorizeHttpRequests(authz -> authz
			.requestMatchers("/login/**","/employee/showList").permitAll()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/img/**").permitAll()
			.requestMatchers("/**").permitAll()
			.anyRequest().authenticated()
		)
        .logout(logout -> logout
            .permitAll()
        );

		System.out.println("認証");
		System.out.printf("admin1234 -> [%s]\n", passwordEncoder().encode("admin1234"));
		System.out.printf("student5678 -> [%s]\n", passwordEncoder().encode("student5678"));

	return http.build();
	}

	//     @Bean
    // public UserDetailsManager userDetailsManager() {
    //     JdbcUserDetailsManager user = new JdbcUserDetailsManager(this.dataSource);
    //     // ユーザーを追加したい時
	// // user.createUser(makeUser("user", "pass", "USER"));

    //     return user;
    // }

    // private UserDetails makeUser(String user, String pass, String role) {
    //     return User.withUsername(user)
    //             .password(passwordEncoder().encode(pass))
    //             .roles(role)
    //             .disabled(false)
    //             .build();
    // }

}
