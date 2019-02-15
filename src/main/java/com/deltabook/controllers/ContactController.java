package com.deltabook.controllers;


import com.deltabook.services.ContactService;
import com.deltabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;


}
