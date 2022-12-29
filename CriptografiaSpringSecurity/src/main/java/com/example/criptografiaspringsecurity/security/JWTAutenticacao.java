package com.example.criptografiaspringsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.criptografiaspringsecurity.data.UsuarioData;
import com.example.criptografiaspringsecurity.model.UsuarioModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticacao extends UsernamePasswordAuthenticationFilter {
    public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "23e2dd6a-6e6d-11ed-a1eb-0242ac120002";
    private final AuthenticationManager authenticationManager;

    public JWTAutenticacao(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // realiza a autenticacao das informacoes de acesso

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), UsuarioModel.class);
            // ObjectMapper -> Json para Objeto

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                // login, senha, lista de permissoes
               usuario.getLogin(), usuario.getPassword(), new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // funciona como uma factory de token JWT
    protected void successfulAuthentication (
            HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication
    ) throws IOException, ServletException {
        UsuarioData usuarioData = (UsuarioData) authentication.getPrincipal();
        String token = JWT
                .create()
                .withSubject(usuarioData.getUsername()) // usuario
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO)) // tempo de expiracao
                .sign(Algorithm.HMAC512(TOKEN_SENHA)); // realiza a decomposicao da assinatura da senha

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
