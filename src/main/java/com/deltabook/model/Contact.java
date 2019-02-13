package com.deltabook.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    public Contact() {}
    public Contact(User friendFromId,User friendToId ) {
        this.friendFromId=friendFromId;
        this.friendToId=friendToId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friendFromId")
    private User friendFromId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friendToId")
    private User friendToId;
    private boolean isAccepted;
    @Lob
    private String requestMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFriendFromId() {
        return friendFromId;
    }

    public void setFriendFromId(User friendFromId) {
        this.friendFromId = friendFromId;
    }

    public User getFriendToId() {
        return friendToId;
    }

    public void setFriendToId(User friendToId) {
        this.friendToId = friendToId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

}
