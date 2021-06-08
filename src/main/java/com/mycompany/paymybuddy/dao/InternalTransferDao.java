package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.InternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalTransferDao extends JpaRepository<InternalTransfer, Integer> {
}
