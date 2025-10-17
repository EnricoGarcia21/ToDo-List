package com.enrico.projeto_user.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.enrico.projeto_user.user.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component // toda a classe que o spring gerenciar tem que utilzar o component ou restcontroller
public class FilterTaskAuth extends OncePerRequestFilter {


    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            var servletPath = request.getServletPath();
            if(servletPath.startsWith("/task")){
                var autorization = request.getHeader("Authorization");

                //metodo para receber como parametro, paar pegar o asic e cacular o tamanho dela
                var user_pass = autorization.substring("Basic".length()).trim();
                byte[] auhDecode=  Base64.getDecoder().decode(user_pass);
                var authString = new String(auhDecode);

                String[] credencial = authString.split(":");
                String userName = credencial[0];
                String password = credencial[1];
                System.out.println("Authorization: ");

                //validar usuario

                var user = this.userRepository.findByUserName(userName);
                if(user==null){
                    response.sendError(401);
                }else{
                    //validar senha
                    var result = BCrypt.verifyer().verify(password.toCharArray(),user.getPassword());
                    if(result.verified){
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request,response);
                    }else{
                        response.sendError(401);
                    }

                }
            }else{
                filterChain.doFilter(request,response);
            }



    }


}
