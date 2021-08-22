package com.mycompany.paymybuddy.dao;

import com.mycompany.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// < entité, type de Id>
public interface BankAccountDAO extends JpaRepository <BankAccount, String> {

    BankAccount findBankAccountByIban(String iban);

    List<BankAccount> findBankAccountsByUser_Email(String emailOwner);

    BankAccount findBankAccountByIbanAndUser_Email(String ibanUser, String emailUser);

}
