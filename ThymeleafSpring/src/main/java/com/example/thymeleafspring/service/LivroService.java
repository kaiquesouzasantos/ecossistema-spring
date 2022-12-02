package com.example.thymeleafspring.service;

import com.example.thymeleafspring.model.Livro;
import com.example.thymeleafspring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired private LivroRepository livroRepository;

    public Livro getLivro(Long id){
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> getLivroLista() {
        // Iterable -> Stramable -> List
        return Streamable.of(livroRepository.findAll()).toList();
    }

    public void save(Livro livro){
        livroRepository.save(livro);
    }

    public void delete(Long id){
        livroRepository.deleteById(id);
    }
}