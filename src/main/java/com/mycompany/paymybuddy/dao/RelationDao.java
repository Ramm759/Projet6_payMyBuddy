package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationDao extends JpaRepository<Relation, Integer> {
}
