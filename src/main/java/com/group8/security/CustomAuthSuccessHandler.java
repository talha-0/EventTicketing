package com.group8.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("User authorities: " + authorities);
        if (authorities.contains(new SimpleGrantedAuthority("Admin"))) {
            response.sendRedirect("/admin/home");
        } else if (authorities.contains(new SimpleGrantedAuthority("Customer"))) {
            response.sendRedirect("/customer/home");
        }else if (authorities.contains(new SimpleGrantedAuthority("Host"))) {
            response.sendRedirect("/host/home");
        } else {
                response.sendRedirect("/logout");
        }
    }
}