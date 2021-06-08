package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.ExternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalTransferDao extends JpaRepository<ExternalTransfer, Integer> {
}
