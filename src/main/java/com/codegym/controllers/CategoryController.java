package com.codegym.controllers;

import com.codegym.models.Category;
import com.codegym.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/category-list")
    public ModelAndView listCategory(@PageableDefault(size=5) Pageable pageable ){
        Page<Category> categories = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("categories/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/category-create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("categories/create");
        modelAndView.addObject("categories", new Category());
        return modelAndView;
    }

    @PostMapping("category-create")
    public ModelAndView saveCategory(@ModelAttribute("categories") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("categories/create");
        modelAndView.addObject("categories", new Category());
        modelAndView.addObject("message", "You have just created a new category");
        return modelAndView;
    }

    @GetMapping("category-edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("categories/edit");
            modelAndView.addObject("categories", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("category-edit")
    public ModelAndView doEdit(@ModelAttribute ("categories") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("categories/edit");
        modelAndView.addObject("categories", new Category());
        modelAndView.addObject("message", "Edited this category");
        return modelAndView;
    }

    @GetMapping("category-delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("categories/delete");
            modelAndView.addObject("categories", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("category-delete")
    public String delete(@ModelAttribute ("categories") Category category){
        categoryService.remove(category.getCategoryId());
        return "redirect:category-list";

    }
}