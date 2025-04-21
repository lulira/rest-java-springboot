package br.com.lulira.repository;

import br.com.lulira.model.Book;
import br.com.lulira.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
