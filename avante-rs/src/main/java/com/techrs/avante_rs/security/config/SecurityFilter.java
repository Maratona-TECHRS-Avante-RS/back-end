package com.techrs.avante_rs.security.config;

import com.techrs.avante_rs.security.config.user.LoadUserService;
import com.techrs.avante_rs.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoadUserService loadUserService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);

        if(token != null){
            String email = tokenService.validateToken(token);
            if(email != null){
                UserDetails user = loadUserService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        String bearerTag = "Bearer ";
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith(bearerTag)) return null;
        return authHeader.replace(bearerTag, "");
    }
}
