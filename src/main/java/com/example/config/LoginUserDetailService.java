package com.example.config;

import java.beans.JavaBean;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserDetailService implements UserDetailsService {
  
  private final AdministratorRepository administratorRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("入力したメールアドレス"+email);
    System.out.println("LoginUserDetailLoadByUsename");
    Optional<Administrator> _user = Optional.ofNullable(administratorRepository.findByMailAddress(email));
    return _user.map(user -> new LoginUserDetails(user))
        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
  }
}
