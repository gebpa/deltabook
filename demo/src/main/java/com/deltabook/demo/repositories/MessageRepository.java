package com.deltabook.demo.repositories;

import com.deltabook.demo.model.Message;
import com.deltabook.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRecipientIDAndSenderIDOrderByCreatedAt(User senderId, User recipientId);

}
