package com.se.buitanphattuan04.dao;

import com.se.buitanphattuan04.beans.Book;

import java.util.List;

public interface BookDAO {
    public void addBook(Book book);

    public Book getBook(String id);

    public List<Book> getAllBooks();
}
