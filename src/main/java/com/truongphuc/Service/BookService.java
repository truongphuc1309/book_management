package com.truongphuc.Service;

import com.truongphuc.Dao.BookDao;
import com.truongphuc.Model.Book;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class BookService {
    public boolean create(String name, String author, String type) {

        Random rd = new Random();
        int rdNumber = rd.nextInt(1000);
        Date date = new Date();
        long timeMilli = date.getTime();
        String id = "" + timeMilli + rdNumber;

        Book book = new Book(id, name, author, type);

        BookDao bookDao = new BookDao();
        Book result = bookDao.save(book);
        return result != null;
    }

    public List<Book> getAll(){
        BookDao bookDao = new BookDao();
        return bookDao.findAll();
    }

    public boolean delete(String id) {
        BookDao bookDao = new BookDao();
        return bookDao.delete(id);
    }

    public boolean update(String id, String name, String author, String type) {
        Book book = new Book(id, name, author, type);
        BookDao bookDao = new BookDao();
        return bookDao.update(book);
    }
}
