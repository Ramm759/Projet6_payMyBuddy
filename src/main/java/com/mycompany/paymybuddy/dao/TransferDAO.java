package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferDAO extends JpaRepository<Transfer, Integer> {
}
