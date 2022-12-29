package com.example.criptografiaspringsecurity.service;

import com.example.criptografiaspringsecurity.data.UsuarioData;
import com.example.criptografiaspringsecurity.model.UsuarioModel;
import com.example.criptografiaspringsecurity.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findByLogin(username);

        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("USUARIO ["+ username +"] NAO ENCONTRADO");
        }

        return new UsuarioData(usuario);
    }

    public UsuarioModel save(UsuarioModel usuarioModel) {
        return repository.save(usuarioModel);
    }

    public Optional<UsuarioModel> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public List<UsuarioModel> findAll() {
        return repository.findAll();
    }
}
