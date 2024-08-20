package com.truongphuc.Dao;

import com.truongphuc.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao {
    public User findByUsername(String username) {
        Connection con = getConnection();
        User result = null;

        if (con != null) {
            String query = "SELECT * FROM user WHERE user_name=?";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                preSt.setString(1, username);

                ResultSet rs = preSt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String fullname = rs.getString("full_name");
                    String password = rs.getString("password");
                    result = new User(id, username, fullname, password);
                }

                con.close();
                preSt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public User save(User user) {
        Connection con = getConnection();
        User result = null;

        if (con != null) {
            String query = "INSERT INTO `user`(`id`, `user_name`, `full_name`, `password`) VALUES (? ,?, ?, ?)";
            try {
                PreparedStatement preSt = con.prepareStatement(query);
                preSt.setString(1, user.getId());
                preSt.setString(2, user.getUserName());
                preSt.setString(3, user.getFullName());
                preSt.setString(4, user.getPassword());
                if (preSt.executeUpdate() > 0) result = user;

                con.close();
                preSt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }
}
