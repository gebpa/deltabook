package com.deltabook.model.send;

import com.deltabook.model.Message;

import java.sql.Timestamp;

public class SendMessage {

    public SendMessage() {
    }

    public SendMessage(Message message){
        this.id = message.getId();
        this.body = message.getBody();
        this.timestamp =message.getCreatedAt();
        this.nickName =message.getSenderID().getLogin();
    }

    private Long id;

    private String body;

    private String nickName;

    private Timestamp timestamp;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        body = body;
    }

    public String getNickanme() {
        return nickName;
    }

    public void setNickanme(String nickanme) {
        nickName = nickanme;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
