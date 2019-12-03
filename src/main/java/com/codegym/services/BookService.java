package com.codegym.services;

import com.codegym.models.Book;
import com.codegym.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Iterable<Book> findAll();

    Book findById (Long id);

    void save(Book book);

    void remove(Long id);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByCategory(Category category, Pageable pageable);

    Page<Book> findAllByBookName(String bookName, Pageable pageable);

    Page<Book> findAllByAuthor( String author, Pageable pageable);
}
