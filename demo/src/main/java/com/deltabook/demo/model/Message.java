package com.deltabook.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

import java.util.Date;
@Entity
@Table(name = "messages")
public class Message {
    public Message() {}

    public Message(User senderID, User recipientID, String body ) {
        this.senderID = senderID;
        this.recipientID=recipientID;
        this.body=body;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.createdAt = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_ID")
    private User senderID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_ID")
    private User recipientID;
    @Lob
    private String body;
    private Timestamp createdAt;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSenderID() {
        return senderID;
    }

    public void setSenderID(User senderID) {
        this.senderID = senderID;
    }

    public User getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(User recipientID) {
        this.recipientID = recipientID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
