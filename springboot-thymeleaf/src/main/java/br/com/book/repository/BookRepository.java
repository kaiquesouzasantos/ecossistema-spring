package br.com.book.repository;

import br.com.book.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

}
