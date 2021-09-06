package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dao.RelationDAO;
import com.mycompany.paymybuddy.dao.RoleDAO;
import com.mycompany.paymybuddy.dao.UserDAO;
import com.mycompany.paymybuddy.dto.UserRegistrationDTO;
import com.mycompany.paymybuddy.exceptions.DataAlreadyExistException;
import com.mycompany.paymybuddy.exceptions.DataNotFindException;
import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.model.Role;
import com.mycompany.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private RoleDAO roleDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RelationDAO relationDao;

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // 12 = force de l'encodage

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        Role role = roleDao.findRoleByName("USER");
        User user = new User(userRegistrationDTO.getLastname(), userRegistrationDTO.getFirstname(), userRegistrationDTO.getEmail(), encoder.encode(userRegistrationDTO.getPassword()), BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()), Arrays.asList(role));
        return userDAO.save(user);
    }

    @Override
    public List<Relation> listEmailsRelation(String emailOwner) {

        return relationDao.findAllByOwner_Email(emailOwner);
    }

    @Override
    public Relation addBuddy(String emailOwner, String emailBuddy) {
        Relation relation = new Relation();
        User owner = userDAO.findByEmail(emailOwner);
        User buddy = userDAO.findByEmail(emailBuddy);
        relation.setOwner(owner);
        relation.setBuddy(buddy);
        if (relation.getBuddy()==null){
            throw new DataNotFindException("Cet utilisateur n'existe pas");
        }
        // On cherche si la relation existe déjà
        for (Relation relation1: relation.getOwner().getRelations()){
            if (relation1.getBuddy().equals(relation.getBuddy())){
                throw new DataAlreadyExistException("Cette personne est déjà votre ami");
            }
        }
        relationDao.save(relation);
        return relation;
    }

    @Override
    public Boolean delRelation(Integer id) {
        if (relationDao.existsById(id)){
            relationDao.deleteById(id);
            return true;
        }
        return false;
    }
}
