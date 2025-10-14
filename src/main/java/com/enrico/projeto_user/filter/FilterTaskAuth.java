package com.enrico.projeto_user.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // toda a classe que o spring gerenciar tem que utilzar o component ou restcontroller
public class FilterTaskAuth extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            var autorization = request.getHeader("Authorization");
        System.out.println(autorization);
            filterChain.doFilter(request, response);
    }
}
