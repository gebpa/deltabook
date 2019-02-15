package com.deltabook.controllers;

import com.deltabook.model.User;
import com.deltabook.model.send.SendChangeUser;
import com.deltabook.security.details.UserDetailsImpl;
import com.deltabook.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private UserServiceImpl UserServiceImpl;

    @RequestMapping("/main_admin")
    public String MainAdmin(Authentication authentication, Model model) {
        model.addAttribute("SendChangeUser", new SendChangeUser());
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        User user = principal.getUser();
        switch(user.getRole()) {
            case ROLE_USER: return ("/");
            case ROLE_ADMIN: return("/main_admin");
        }
        return "main_admin";
    }
    @RequestMapping("/change_user_last_name")
    public String changeUserLastName(Authentication authentication, Model model, @ModelAttribute SendChangeUser SendChangeUser) {
        User user = UserServiceImpl.getUserByLogin(SendChangeUser.getNickName());
        user.setLastName(SendChangeUser.getNewLastName());
        UserServiceImpl.SaveUser(user);
        model.addAttribute("SendChangeUser", new SendChangeUser());
        return "main_admin";
    }
    @RequestMapping("/delete_user")
    public String DeleteUser(Authentication authentication, Model model, @ModelAttribute SendChangeUser SendChangeUser) {
        User user = UserServiceImpl.getUserByLogin(SendChangeUser.getNickName());
        UserServiceImpl.deleteUser(user);
        model.addAttribute("SendChangeUser", new SendChangeUser());
        return "main_admin";
    }
}
