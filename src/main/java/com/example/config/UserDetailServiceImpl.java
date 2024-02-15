package com.example.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
    
    private final AdministratorRepository administratorRepository;
    // private final PasswordEncoder passwordEncoder;
    /**
     * ユーザー作成
     * 
     * @param username mailAddress
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Administrator userInfo = Optional.ofNullable(administratorRepository.findByMailAddress(username))
                        .orElseThrow(()->new UsernameNotFoundException(username));
        return User.withUsername(userInfo.getMailAddress())
                .password(userInfo.getPassword())
                .roles("USER")
                .build();           
    }    
}
