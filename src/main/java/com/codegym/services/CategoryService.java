package com.codegym.services;

import com.codegym.models.Category;
import com.codegym.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById (Long id);

    void save(Category category);

    void remove(Long id);

    Page<Category> findAll (Pageable pageable);




}