package com.github.albertloubet.libraryfx.service;

import com.github.albertloubet.libraryfx.model.entity.Book;
import com.github.albertloubet.libraryfx.model.filter.BookFilter;
import com.github.albertloubet.libraryfx.repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService() {
        bookRepository = new BookRepository();
    }

    public List<Book> getAll(BookFilter filter) {
        return bookRepository.findAllByFilters(filter);
    }
}