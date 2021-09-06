package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findRoleByName(String user);
}
