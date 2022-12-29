package com.example.criptografiaspringsecurity.repository;

import com.example.criptografiaspringsecurity.model.UsuarioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByLogin(String login);
}
