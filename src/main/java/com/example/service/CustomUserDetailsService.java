package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Administrator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final AdministratorService administratorService;

    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        Administrator user = administratorService.findByMailAddress(mailAddress).
                                orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + mailAddress);
        }

        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .build();
    }

}
