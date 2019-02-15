package com.deltabook.controllers;


import com.deltabook.model.User;
import com.deltabook.model.send.SendFriendRequest;
import com.deltabook.security.details.UserDetailsImpl;
import com.deltabook.services.ContactService;
import com.deltabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/send_request")
    String sendRequest(Model model) {
        model.addAttribute("sendFriendRequest", new SendFriendRequest());
        return "send_request";
    }
    @PostMapping("/send_request")
    String sendRequest(Authentication authentication, Model model, @ModelAttribute SendFriendRequest send_req) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        User userFrom = principal.getUser();
        User userTo = userService.getUserByLogin(send_req.getFriendNickname());
        contactService.sendRequestFriend(userFrom, userTo,send_req.getRequestMessage() );
        return "main";
    }
}
