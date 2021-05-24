package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.BankAccountDTO;
import com.mycompany.paymybuddy.model.BankAccount;

import java.util.List;

public interface IBankAccountService {
    BankAccount addBankAccount (String emailOwner, BankAccountDTO bankAccountDTO);
    List<BankAccount> listBankAccountByUser (String emailOwner);
    Boolean delBankAccount (String ibanBankAccount);
}
