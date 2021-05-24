package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.BankAccountDTO;
import com.mycompany.paymybuddy.model.BankAccount;

import java.util.List;

public class BankAccountService implements IBankAccountService{

    @Override
    public BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO) {
        return null;
    }

    @Override
    public List<BankAccount> listBankAccountByUser(String emailOwner) {
        return null;
    }

    @Override
    public Boolean delBankAccount(String ibanBankAccount) {
        return null;
    }
}
