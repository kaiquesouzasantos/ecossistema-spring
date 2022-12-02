package com.example.thymeleafspring.repository;

import com.example.thymeleafspring.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {}
