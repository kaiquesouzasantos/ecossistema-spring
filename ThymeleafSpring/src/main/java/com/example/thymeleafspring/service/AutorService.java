package com.example.thymeleafspring.service;

import com.example.thymeleafspring.model.Autor;
import com.example.thymeleafspring.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAutorLista() {
        // Iterable -> Stramable -> List
        return Streamable.of(autorRepository.findAll()).toList();
    }

    public void save(Autor autor){
        autorRepository.save(autor);
    }
}
