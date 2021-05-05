package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// < entité, type de Id>
public interface UserDAO extends JpaRepository<User, Integer> {

}
