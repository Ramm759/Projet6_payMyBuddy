package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.ExternalTransfer;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalTransferDAO extends JpaRepository<ExternalTransfer, Integer> {
    List<ExternalTransfer> findAllByBankAccount_User_EmailOrderByTransactionDateDesc(String email);
}
