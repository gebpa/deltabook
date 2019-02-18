package com.deltabook.services;

import com.deltabook.model.Message;
import com.deltabook.model.User;
import com.deltabook.model.send.SendMessage;
import com.deltabook.repositories.MessageRepository;
import com.deltabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    public Message sendMessage(User userFrom, SendMessage sendMessage) {
        User userTo = userRepository.findUserByLogin(sendMessage.getNickName());
        String messageBody = sendMessage.getBody();
        Message message = messageRepository.save(new Message(userFrom, userTo, messageBody));
        return message;
    }

    @Override
    public Message getLastUnreadMessage(User recipientId) {
        return messageRepository.findFirstByRecipientIDAndIsReadFalseOrderByCreatedAtDesc(recipientId);
    }

    @Override
    public List<Message> getDialog(User recipientId, User senderId) {
        List<Message> messageList = new ArrayList<Message>();
        messageList = messageRepository.findMessagesBySenderIDAndRecipientID(senderId, recipientId);
        return messageList;
    }

    @Override
    public List<User> findMessagesByRecipientID(User senderId) {
        List<User> sendersList = new ArrayList<User>();
        List<Message> messageList = new ArrayList<Message>();
        messageList = messageRepository.findByRecipientID(senderId);
        for (Message msg : messageList) {
            sendersList.add(msg.getSenderID());
        }
        Set<User> set = new HashSet<>(sendersList);
        sendersList.clear();
        sendersList.addAll(set);
        return sendersList;
    }

}
