package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.UserRegistrationDTO;
import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.model.User;

import java.util.List;

public interface IUserService {
    User save(UserRegistrationDTO userRegistrationDTO);

    List<Relation> listEmailsRelation(String emailOwner);

    Relation addBuddy(String emailOwner, String emailBuddy);

    Boolean delRelation(Integer id);
}
