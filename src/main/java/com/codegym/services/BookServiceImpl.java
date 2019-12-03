package com.codegym.services;

import com.codegym.models.Book;
import com.codegym.models.Category;
import com.codegym.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);

    }

    @Override
    public void remove(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByCategory(Category category, Pageable pageable) {
        return bookRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Book> findAllByBookName(String bookName, Pageable pageable) {
        return bookRepository.findAllByBookName(bookName, pageable);
    }

    @Override
    public Page<Book> findAllByAuthor(String author, Pageable pageable) {
        return bookRepository.findAllByAuthor(author, pageable);
    }
}