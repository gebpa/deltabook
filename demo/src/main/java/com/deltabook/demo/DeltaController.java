package com.deltabook.demo;

import com.deltabook.demo.model.*;
import com.deltabook.demo.repository.ContactRepository;
import com.deltabook.demo.repository.MessageRepository;
import com.deltabook.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class DeltaController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MessageRepository messageRepository;

    private User currentUser;
    @RequestMapping("/")
    public String MainPage(Model model) {
        model.addAttribute("objectToFill_auth", new User ());
        return "main";
    }

    @RequestMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("objectToFill", new User ());
        return "registration";
    }

    @RequestMapping( value = "/enter_reg", method = RequestMethod.POST)
    String reg(@ModelAttribute User insertedObject, Model model) {
        userRepository.save(new User(insertedObject.getLogin(), insertedObject.getPassword(),insertedObject.getFirstName(), insertedObject.getLastName() ));
        model.addAttribute("objectToFill_auth", new User ());
        return "main";
    }
    @RequestMapping( value = "/enter_auth", method = RequestMethod.POST)
    String auth(@ModelAttribute("UserObj") User insertedObject, Model model) {
        model.addAttribute("objectToFill_auth", new User ());
        User user =  userRepository.findLogPass(insertedObject.getLogin(), insertedObject.getPassword());

        if(user == null)
        return "error_auth";

        currentUser = user;
        return "user_panel";

    }
    @RequestMapping( value = "/user_panel")
    String user_panel(Model model) {
        return "user_panel";
    }
    @RequestMapping( value = "/send_message")
    String send_message(Model model) {
        model.addAttribute("recipient", new SendMessage ());
        return "send_message";
    }
    @RequestMapping( value = "/add_user")
    String add_user(Model model) {
        model.addAttribute("user_to", new User ());
        return "add_user";
    }
    @RequestMapping( value = "/enter_add_friend", method = RequestMethod.POST)
    String enter_add_friend(Model model, @ModelAttribute User user_to) {

        User correct_user_to = userRepository.findLog(user_to.getLogin());
        contactRepository.save(new Contact(currentUser, correct_user_to));
        return "user_panel";
    }
    @RequestMapping( value = "/enter_message_data")
    String send_message(Model model, @ModelAttribute SendMessage recipient) {

        User correct_recipient = userRepository.findLog(recipient.getNickanme());
        System.out.println(recipient.getBody());
        messageRepository.save(new Message(currentUser,correct_recipient,recipient.getBody()));
        return "user_panel";
    }
}
