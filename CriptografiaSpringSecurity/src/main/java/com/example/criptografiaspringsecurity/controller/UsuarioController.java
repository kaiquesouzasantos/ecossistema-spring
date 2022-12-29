package com.example.criptografiaspringsecurity.controller;

import com.example.criptografiaspringsecurity.model.UsuarioModel;
import com.example.criptografiaspringsecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired private UsuarioService service;
    // PasswordEncoder -> classe que permite a criptografia de uma informação, atravaes do metodo enconde()
    @Autowired private PasswordEncoder encoder;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        return  ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuarioModel){
        // @RequestBody <tipoObjeto> -> converte o JSON contido no corpo da request no objeto
        usuarioModel.setPassword(
                encoder.encode(usuarioModel.getPassword())
        );
        // uma mesma string apos o encode obtem resultados diferentes.
        return ResponseEntity.ok(service.save(usuarioModel));
    }

    @GetMapping("/validar-senha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password){
        Optional<UsuarioModel> usuario = service.findByLogin(login);
        boolean senhaValida = false;

        if(usuario.isPresent()){
            senhaValida = encoder.matches(password, usuario.get().getPassword());
        }

        HttpStatus status = (senhaValida) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(senhaValida);
    }
}
