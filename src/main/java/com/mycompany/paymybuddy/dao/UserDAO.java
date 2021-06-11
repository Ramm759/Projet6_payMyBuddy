package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// < entité, type de Id>
// l'objet qui se connecte est un User
public interface UserDAO extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);
}
