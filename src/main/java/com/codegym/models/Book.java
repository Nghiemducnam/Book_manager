package com.codegym.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
@NotEmpty
    private String bookName;
@NotEmpty
    private String dateOfPurchase;
@NotEmpty
    private String author;
@Min(0)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Book() {
    }

    public Book(String bookName, String dateOfPurchase, String author, Long price, Category category) {
        this.bookName = bookName;
        this.dateOfPurchase = dateOfPurchase;
        this.author = author;
        this.price = price;
        this.category = category;
    }
    //    public Book(@NotEmpty String bookName, @NotEmpty String dateOfPurchase, @NotEmpty String author, @NotEmpty Long price, @NotEmpty Category category) {
//        this.bookName = bookName;
//        this.dateOfPurchase = dateOfPurchase;
//        this.author = author;
//        this.price = price;
//        this.category = category;
//    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}