package com.deltabook.demo.repositories;

import com.deltabook.demo.model.Contact;
import com.deltabook.demo.model.User;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByIsAcceptedAndFriendToId(boolean isAccepted, User friendToId);

    List<Contact> findByFriendFromIdAndFriendToId(User friendToId, User friendFromId);

}
