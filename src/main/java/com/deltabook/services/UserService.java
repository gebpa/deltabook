package com.deltabook.services;

import com.deltabook.model.User;

public interface UserService {

    User getUserByLogin(String login);
    String registerUser(User user);
    String updateUser(User newUser, User oldUser);
    void deleteUser(User user);
}