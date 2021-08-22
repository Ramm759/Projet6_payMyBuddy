package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dao.*;
import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;
import com.mycompany.paymybuddy.model.BankAccount;
import com.mycompany.paymybuddy.model.ExternalTransfer;
import com.mycompany.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService implements ITransferService{
    @Autowired
    private TransferDao transferDao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RelationDao relationDao;

    @Autowired
    private InternalTransferDao internalTransferDao;

    @Autowired
    private ExternalTransferDao externalTransferDao;

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public InternalTransferDTO createInternalTransfer(InternalTransferDTO internalTransferDTO) {
        return null;
    }

    @Override
    public ExternalTransferDTO createExternalTransfer(ExternalTransferDTO externalTransferDTO) {
        // On a besoin de récupérer le bankAccount de User
        BankAccount bankAccount = bankAccountDAO.findBankAccountByIbanAndUser_Email(externalTransferDTO.getIbanUser(), externalTransferDTO.getEmailUser());
        User user = bankAccount.getUser();

        BigDecimal fees = externalTransferDTO.getAmountUser().multiply(BigDecimal.valueOf(0.005));

        ExternalTransfer  externalTransfer = new ExternalTransfer();
        externalTransfer.setAmount(externalTransferDTO.getAmountUser());
        externalTransfer.setDescription(externalTransferDTO.getDescription());
        externalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        externalTransfer.setStatus("Completed");
        externalTransfer.setFees(fees);
        externalTransfer.setBankAccount(bankAccount);

        transferDao.save(externalTransfer);

        // On récupere l'Id de l'enregistrement
        externalTransferDTO.setId(externalTransfer.getId());
        user.setBalance(user.getBalance().add(externalTransfer.getAmount().subtract(fees)));
        userDAO.save(user);

        return  externalTransferDTO;
    }

    @Override
    public List<InternalTransferDTO> listInternalTransfer(String emailOwner) {
        return null;
    }

    @Override
    public List<ExternalTransferDTO> listExternalTransfer(String emailOwner) {
        return null;
    }
}
