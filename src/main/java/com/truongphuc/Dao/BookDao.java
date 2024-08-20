package com.truongphuc.Dao;

import com.truongphuc.Model.Book;
import com.truongphuc.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDao{
    public Book save(Book book) {
        Connection con = getConnection();
        Book result = null;

        if (con != null) {
            String query = "INSERT INTO `book`(`id`, `name`, `author`, `type`) VALUES (? ,?, ?, ?)";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                preSt.setString(1, book.getId());
                preSt.setString(2, book.getName());
                preSt.setString(3, book.getAuthor());
                preSt.setString(4, book.getType());
                if (preSt.executeUpdate() > 0) result = book;

                con.close();
                preSt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public List<Book> findAll() {
        List<Book> result = new ArrayList<Book>();
        Connection con = getConnection();

        if (con != null) {
            String query = "SELECT * FROM book";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                ResultSet rs = preSt.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String author = rs.getString("author");
                    String type = rs.getString("type");
                    Book book = new Book(id, name, author, type);
                    result.add(book);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public boolean delete(String id) {
        Connection con = getConnection();
        boolean result = false;

        if (con != null) {
            String query = "DELETE FROM `book` WHERE id = ?";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                preSt.setString(1, id);

                if (preSt.executeUpdate() > 0) result = true;
                con.close();
                preSt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public boolean update(Book book) {
        Connection con = getConnection();
        boolean result = false;

        if (con != null) {
            String query = "UPDATE `book` SET `name`=?, `author`=?, `type`=? WHERE `id`=?";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                preSt.setString(1, book.getName());
                preSt.setString(2, book.getAuthor());
                preSt.setString(3, book.getType());
                preSt.setString(4, book.getId());

                if (preSt.executeUpdate() > 0) result = true;
                con.close();
                preSt.close();

                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }
}
