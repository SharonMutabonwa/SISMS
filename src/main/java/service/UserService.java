/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Odeth
 */

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPasswordHash().equals(PasswordUtil.hashPassword(password))) {
            return user;
        }
        return null;
    }

    public boolean registerUser(User user) {
        // Always hash the password before saving
        user.setPasswordHash(PasswordUtil.hashPassword(user.getPasswordHash()));
        return userDAO.registerUser(user);
    }
}

