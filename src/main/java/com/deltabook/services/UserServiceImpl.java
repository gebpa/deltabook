package com.deltabook.services;


import com.deltabook.model.User;
import com.deltabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public String registerUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            return "There is already a user with this login";
        }
//        if (user.getPassword().length() < 5) {
//            return "Password is too small";
//        }
//        String pattern = "(?=.*[0-9])(?=.*[a-z]).{5,}";
//        if (!user.getPassword().matches(pattern)) {
//            return "Password has to contain at least one digit and and at least one letter";
//        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.save(new User(user.getLogin(), hashedPassword, user.getFirstName(), user.getLastName()));
        return "Success";
    }

    public String updateUser(User newUser, User oldUser) {
        if (passwordEncoder.encode(newUser.getPassword()).equals(passwordEncoder.encode(oldUser.getPassword()))){
            return "Wrong Password";
        }
        if (userRepository.findUserByLogin(newUser.getLogin()) != null && userRepository.findUserByLogin(newUser.getLogin()) != oldUser) {
            return "There is already a user with this login";
        }
        newUser.setId(oldUser.getId());
        userRepository.saveAndFlush(newUser);
        return "Success";
    }

    public void deleteUser(User user) {
        user.setDeleted(true);
        userRepository.saveAndFlush(user);
    }

    public void SaveUser(User user) {
        userRepository.save(user);
    }
    public void SaveAndFlushUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
