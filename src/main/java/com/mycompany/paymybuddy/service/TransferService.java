package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dao.*;
import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;
import com.mycompany.paymybuddy.exceptions.DataNotExistException;
import com.mycompany.paymybuddy.exceptions.DataNotFindException;
import com.mycompany.paymybuddy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TransferService implements ITransferService {
    @Autowired
    private TransferDAO transferDao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RelationDAO relationDao;

    @Autowired
    private InternalTransferDAO internalTransferDao;

    @Autowired
    private ExternalTransferDAO externalTransferDao;

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public InternalTransferDTO createInternalTransfer(InternalTransferDTO internalTransferDTO) throws SQLException {
        // On récupère la relation entre les 2 users
        Relation relation = relationDao.findByOwner_EmailAndBuddy_Email(internalTransferDTO.getEmailSender(), internalTransferDTO.getEmailReceiver());

        if (relation == null) {
            throw new DataNotFindException("Les 2 users ne sont pas amis");
        }

        // On vérifie si il a essez d'argent sur le compte
        if (internalTransferDTO.getAmountUser().compareTo(relation.getOwner().getBalance()) > 0) {
            throw new DataNotExistException("Fonds insufisants");
        }

        InternalTransfer internalTransfer = new InternalTransfer();
        internalTransfer.setUserSender(relation.getOwner());
        internalTransfer.setUserReceiver(relation.getBuddy());
        internalTransfer.setStatus("Completed");
        internalTransfer.setAmount(internalTransferDTO.getAmountUser());
        internalTransfer.setDescription(internalTransferDTO.getDescription());
        internalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));

        try {
            transferDao.save(internalTransfer);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("Problème lors de la sauvegarde du transfert interne");
        }
        internalTransferDTO.setId(internalTransfer.getId());

        relation.getOwner().setBalance(relation.getOwner().getBalance().subtract(internalTransferDTO.getAmountUser()));
        relation.getBuddy().setBalance(relation.getBuddy().getBalance().add(internalTransferDTO.getAmountUser()));

        try {
            userDAO.save(relation.getOwner());
            userDAO.save(relation.getBuddy());

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("Problème lors de la sauvegarde du transfert interne");
        }


        return internalTransferDTO;
    }

    @Override
    public ExternalTransferDTO createExternalTransfer(ExternalTransferDTO externalTransferDTO) throws SQLException {
        // On a besoin de récupérer le bankAccount de User
        BankAccount bankAccount = bankAccountDAO.findBankAccountByIbanAndUser_Email(externalTransferDTO.getIbanUser(), externalTransferDTO.getEmailUser());
        User user = bankAccount.getUser();

        BigDecimal fees = externalTransferDTO.getAmountUser().multiply(BigDecimal.valueOf(0.005));

        ExternalTransfer externalTransfer = new ExternalTransfer();
        externalTransfer.setAmount(externalTransferDTO.getAmountUser());
        externalTransfer.setDescription(externalTransferDTO.getDescription());
        externalTransfer.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        externalTransfer.setStatus("Completed");
        externalTransfer.setFees(fees);
        externalTransfer.setBankAccount(bankAccount);

        try {
            transferDao.save(externalTransfer);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("Problème lors de la sauvegarde du transfert interne");
        }

        // On récupere l'Id de l'enregistrement
        externalTransferDTO.setId(externalTransfer.getId());
        user.setBalance(user.getBalance().add(externalTransfer.getAmount().subtract(fees)));
        userDAO.save(user);

        return externalTransferDTO;
    }

    @Override
    public List<InternalTransferDTO> listInternalTransfer(String emailOwner) {
        List<InternalTransferDTO> internalTransferDTOS = new ArrayList<>();

        for (InternalTransfer internalTransfer : internalTransferDao.findAllByUserSender_EmailOrderByTransactionDateDesc(emailOwner)) {
            InternalTransferDTO internalTransferDTO = new InternalTransferDTO();
            internalTransferDTO.setEmailSender(internalTransfer.getUserSender().getEmail());
            internalTransferDTO.setEmailReceiver(internalTransfer.getUserReceiver().getEmail());
            internalTransferDTO.setDescription(internalTransfer.getDescription());
            internalTransferDTO.setId(internalTransfer.getId());
            internalTransferDTO.setAmountUser(internalTransfer.getAmount());

            internalTransferDTOS.add(internalTransferDTO);
        }
        return internalTransferDTOS;
    }

    @Override
    public List<ExternalTransferDTO> listExternalTransfer(String emailOwner) {
        List<ExternalTransferDTO> externalTransferDTOS = new ArrayList<>();

        for (ExternalTransfer externalTransfer : externalTransferDao.findAllByBankAccount_User_EmailOrderByTransactionDateDesc(emailOwner)) {
            ExternalTransferDTO externalTransferDTO = new ExternalTransferDTO();
            externalTransferDTO.setIbanUser(externalTransfer.getBankAccount().getIban());
            externalTransferDTO.setDescription(externalTransfer.getDescription());
            externalTransferDTO.setAmountUser(externalTransfer.getAmount());
            externalTransferDTO.setFees(externalTransfer.getFees());
            externalTransferDTOS.add(externalTransferDTO);
        }

        return externalTransferDTOS;
    }
}
