package com.deltabook.controllers;

import com.deltabook.model.User;
import com.deltabook.security.details.UserDetailsImpl;
import com.deltabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/upload_avatar")
    public String UploadAvatar(Model model) {
        model.addAttribute("msg", "Waiting for upload ");
        return "upload_avatar";
    }

    @PostMapping("/upload_avatar")
    public String UploadAvatarSave(Authentication authentication, Model model, @RequestParam("files") MultipartFile file) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        User user = principal.getUser();
        try {
            userService.uploadAvatar(user, file);
            model.addAttribute("msg", "Successfully uploaded files ");
        } catch (Exception ex) {
            model.addAttribute("msg", ex.getMessage());
        }
        return "upload_avatar";
    }
}
