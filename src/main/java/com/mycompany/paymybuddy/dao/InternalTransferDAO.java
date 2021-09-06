package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.InternalTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternalTransferDAO extends JpaRepository<InternalTransfer, Integer> {
    List<InternalTransfer> findAllByUserSender_EmailOrderByTransactionDateDesc(String emailOwner);
}
