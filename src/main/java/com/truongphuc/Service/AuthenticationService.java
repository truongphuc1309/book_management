package com.truongphuc.Service;

import com.truongphuc.Dao.UserDao;
import com.truongphuc.Model.User;

import java.util.Date;
import java.util.Random;

public class AuthenticationService {
    public User register (String username, String fullName, String password) {
        UserDao userDao = new UserDao();
        User foundUser = userDao.findByUsername(username);

        if (foundUser != null)
            throw new RuntimeException("User already exists");

        Random rd = new Random();
        int rdNumber = rd.nextInt(1000);
        Date date = new Date();
        long timeMilli = date.getTime();

        String id = "" + timeMilli + rdNumber;
        User user = new User(id, username, fullName, password);

        return userDao.save(user);
    }

    public User logIn(String userName, String password) {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(userName);
        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }else
            return null;

    }
}
