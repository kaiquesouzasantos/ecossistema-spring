package com.parkingcontrolapi.service;

import com.parkingcontrolapi.model.Usuario;
import com.parkingcontrolapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("USUARIO COM ESSE NOME NAO FOI ENCONTRADO"));
        // User ->
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getFuncoes());
    }
}
