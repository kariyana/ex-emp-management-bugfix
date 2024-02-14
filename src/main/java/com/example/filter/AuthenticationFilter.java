package com.example.filter;

import java.util.Optional;
import java.io.IOException;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

// @Component
// public class AuthenticationFilter implements Filter {

//     public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
//         throws IOException ,ServletException{
//         HttpServletRequest wrapper = (HttpServletRequest) request;
//         //path
//         String path = wrapper.getServletPath();
//         if (!path.matches("/employee.+")) {
//             chain.doFilter(request, response);
//             return;            
//         }
//         //セッションに管理者が存在すれば許可
//         if (Optional.ofNullable(wrapper.getSession(false))
//             .map(s -> s.getAttribute("administrator"))
//             .ifPresent() 
//         ){

//         }
            
//     }
// }
