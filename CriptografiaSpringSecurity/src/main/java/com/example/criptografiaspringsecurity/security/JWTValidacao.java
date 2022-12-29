package com.example.criptografiaspringsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JWTValidacao extends BasicAuthenticationFilter {
    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String PREFIXO_ATRIBUTO = "Bearer ";

    public JWTValidacao(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATRIBUTO);

        if(atributo.isEmpty()){
            chain.doFilter(request, response);
            return;
        }

        if(!atributo.startsWith(PREFIXO_ATRIBUTO)){
            chain.doFilter(request, response);
            return;
        }

        String token = atributo.replace(PREFIXO_ATRIBUTO, ""); // deixa somente o token
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    // retorna o usuario atraves do token
    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String usuario = JWT
                .require(Algorithm.HMAC512(JWTAutenticacao.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

        if(usuario.isEmpty()) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
    }
}
