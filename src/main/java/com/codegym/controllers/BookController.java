package com.codegym.controllers;

import com.codegym.models.Book;
import com.codegym.models.Category;
import com.codegym.services.BookService;
import com.codegym.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

//    @GetMapping("/book-list")
//    public ModelAndView bookList() {
//        bookService.findAll();
//        ModelAndView modelAndView = new ModelAndView("books/list");
//        modelAndView.addObject("books", new Book());
//        return modelAndView;
//    }

@PostMapping("/login")
ModelAndView doLogin(){
    ModelAndView modelAndView = new ModelAndView("/dispatcher/home");
    return  modelAndView;
}
    @GetMapping("/user/book-list")
    public ModelAndView listPhones(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5, sort = "price") Pageable pageable){
        Page<Book> books;
        if(s.isPresent()){
            books = bookService.findAllByBookName(s.get(),pageable);
        }else{
            books = bookService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("books/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @ModelAttribute("categories")
    public Page<Category> categories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/book-create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("books/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/book-create")
    public ModelAndView doCreate(@Validated @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("books/create");
            modelAndView.addObject("message",bindingResult.getAllErrors());
            return modelAndView;
        } else {
            bookService.save(book);
            ModelAndView modelAndView = new ModelAndView("books/create");
            modelAndView.addObject("book", new Book());
            modelAndView.addObject("message", "you have just created a new book");
            return modelAndView;
        }
    }


    @GetMapping("/book-edit/{id}")
    public ModelAndView editFOrm(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            ModelAndView modelAndView = new ModelAndView("books/edit");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }
    @PostMapping("/book-edit")
    public ModelAndView doEdit(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("books/edit");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message","The book is edited successful!");
        return modelAndView;
    }
    @GetMapping("/book-delete/{id}")
    public ModelAndView deleteFOrm(@PathVariable Long id){
        Book book = bookService.findById(id);
        if(book!=null){
            ModelAndView modelAndView = new ModelAndView("books/delete");
            modelAndView.addObject("book", book);
            return modelAndView;
        }else{
        ModelAndView modelAndView = new ModelAndView("error.404");
        return modelAndView;
        }
    }
    @PostMapping("/book-delete")
    public ModelAndView doDelete(@ModelAttribute("book") Book book){
        bookService.remove(book.getBookId());
        ModelAndView modelAndView = new ModelAndView("redirect:book-list");
        return modelAndView;
    }

    @GetMapping("/book-detail/{id}")
    public ModelAndView checkDetail(@PathVariable Long id){
        Book book = bookService.findById(id);
        if(book!=null){
            ModelAndView modelAndView = new ModelAndView("books/detail");
            modelAndView.addObject("book", book);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }
}