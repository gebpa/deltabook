package com.deltabook.controllers;

import com.deltabook.model.*;
import com.deltabook.model.send.SendFriendRequest;
import com.deltabook.model.send.SendMessage;
import com.deltabook.repositories.ContactRepository;
import com.deltabook.repositories.MessageRepository;
import com.deltabook.repositories.UserRepository;
import com.deltabook.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MessageRepository messageRepository;


    @RequestMapping("/")
    public ModelAndView MainPage(Authentication authentication, Model model) {
        model.addAttribute("objectToFill_auth", new User());
        ModelAndView modelAndView = new ModelAndView();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        User user = principal.getUser();
        String image_string;
        if (user.getPicture() != null){
            image_string = Base64.getEncoder().encodeToString(user.getPicture());
            modelAndView.addObject("image", image_string);
        }
        modelAndView.addObject("name", user.getFirstName());
        modelAndView.addObject("surname", user.getLastName());
        switch(user.getRole()) {
            case ROLE_USER: { modelAndView.addObject("role", false); break; }
            case ROLE_ADMIN: { modelAndView.addObject("role", true); break; }
        }
        modelAndView.setViewName("main");
        return modelAndView;
    }
  
    @RequestMapping("/upload_avatar")
    public String UploadPage(Model model) {
        model.addAttribute("msg", "Waiting for upload ");
        return "upload_avatar";
    }

    @RequestMapping("/upload")
    public String upload(Authentication authentication,Model model, @RequestParam("files") MultipartFile file) throws Exception {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        User user = principal.getUser();
        user.setPicture(file.getBytes());
        userRepository.saveAndFlush(user);
        model.addAttribute("msg", "Successfully uploaded files ");
        return "upload_avatar";
    }
}
