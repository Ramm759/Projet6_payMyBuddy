package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.UserRegistrationDTO;
import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        return null;
    }

    @Override
    public List<Relation> listEmailsRelation(String emailOwner) {
        return null;
    }

    @Override
    public Relation addBuddy(String emailOwner, String emailBuddy) {
        return null;
    }

    @Override
    public Boolean delRelation(Integer id) {
        return null;
    }
}
