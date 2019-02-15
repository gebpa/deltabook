package com.deltabook.services;

import com.deltabook.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User getUserByLogin(String login);
    String registerUser(User user);
    String updateUser(User newUser, User oldUser);
    void deleteUser(User user);
    User uploadAvatar(User user,  MultipartFile file) throws Exception;
}
