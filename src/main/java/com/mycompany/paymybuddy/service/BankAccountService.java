package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dao.BankAccountDAO;
import com.mycompany.paymybuddy.dao.UserDAO;
import com.mycompany.paymybuddy.dto.BankAccountDTO;
import com.mycompany.paymybuddy.exceptions.DataAlreadyExistException;
import com.mycompany.paymybuddy.exceptions.DataMissingException;
import com.mycompany.paymybuddy.model.BankAccount;
import com.mycompany.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService implements IBankAccountService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BankAccountDAO bankAccountDAO;


    @Override
    public BankAccount addBankAccount(String emailOwner, BankAccountDTO bankAccountDTO) {
        if (bankAccountDTO.getIban().isBlank()){
            throw new DataMissingException("L'Iban ne peut pas être vide");
        }
        User user = userDAO.findByEmail(emailOwner);
        BankAccount bankAccount = bankAccountDAO.findBankAccountByIban(bankAccountDTO.getIban());
        if (bankAccount == null){
            bankAccount = new BankAccount();
            bankAccount.setIban(bankAccountDTO.getIban());
            bankAccount.setBic(bankAccountDTO.getBic());
            bankAccount.setAccountname(bankAccountDTO.getAccountName());
            bankAccount.setBankname(bankAccountDTO.getBankName());
            bankAccount.setUser(user);
            bankAccountDAO.save(bankAccount);

            return bankAccount;
        }
        else if (bankAccount.getUser().equals(user)){
            throw new DataAlreadyExistException("Vous possédez déjà ce compte bancaire");
        }
        else {
            throw new DataAlreadyExistException("Ce compte bancaire appartient à un autre utilisateur");
        }
    }

    @Override
    public List<BankAccount> listBankAccountByUser(String emailOwner) {
        return bankAccountDAO.findBankAccountsByUser_Email(emailOwner);
    }

    @Override
    public Boolean delBankAccount(String ibanBankAccount) {
        return null;
    }
}
