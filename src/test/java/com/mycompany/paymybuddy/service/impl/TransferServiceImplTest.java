package com.mycompany.paymybuddy.service.impl;

import com.mycompany.paymybuddy.dao.*;
import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.model.Role;
import com.mycompany.paymybuddy.model.Transfer;
import com.mycompany.paymybuddy.model.User;
import com.mycompany.paymybuddy.service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransferServiceImplTest {
    @Autowired
    TransferService transferService;
    @MockBean
    UserDAO userDao;
    @MockBean
    RelationDAO relationDao;
    @MockBean
    TransferDAO transferDao;
    @MockBean
    InternalTransferDAO internalTransferDao;
    @MockBean
    ExternalTransferDAO externalTransferDao;
    @MockBean
    BankAccountDAO bankAccountDao;



    User owner = new User( "barack", "obama", "a@a.com", "1234", BigDecimal.valueOf(111),Timestamp.valueOf(LocalDateTime.now()), ");
    User buddy = new User("george", "bush", "b@b.com", "5678", BigDecimal.valueOf(4321), Timestamp.valueOf(LocalDateTime.now()), "fff");

    Relation relation = new Relation(owner, buddy);
    Transfer transfer = new Transfer();
    Role role = new Role("USER");

    InternalTransferDTO internalTransferDto = new InternalTransferDTO();
    UserRegistrationDTO userRegistrationDto = new UserRegistrationDTO("barack", "obama", "a@a.com", "1234");


    @Test
    void doInternalTransfer() throws SQLException {
        try {
            transferService.doInternalTransfer(internalTransferDto);
            verify(transferDao, times(0)).save(transfer);
            verify(userDao, times(0)).save(owner);
            verify(relationDao, times(1)).findByOwner_EmailAndBuddy_Email(any(), any());

        } catch (DataNotFoundException e) {
            assert (e.getMessage().contains("les 2 users ne sont pas ami"));
        }
    }

    @Test
    void doExternalTransfer() throws SQLException {
        transferService.doInternalTransfer(internalTransferDto);
        verify(transferDao, times(0)).save(transfer);
        verify(userDao, times(0)).save(owner);
        verify(relationDao, times(1)).findByOwner_EmailAndBuddy_Email(any(), any());
    }

    @Test
    void findInternalTransferByUser() {
    }

    @Test
    void findExternalTransferByUser() {
    }
}
