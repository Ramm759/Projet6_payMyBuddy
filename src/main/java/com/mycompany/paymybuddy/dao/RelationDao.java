package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationDao extends JpaRepository<Relation, Integer> {
    List<Relation> findAllByOwner_Email(String emailOwner);
}

