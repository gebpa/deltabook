package com.deltabook.services;

import com.deltabook.model.Message;
import com.deltabook.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    public void SaveMessage(Message message) {
        messageRepository.save(message);
    }
}
