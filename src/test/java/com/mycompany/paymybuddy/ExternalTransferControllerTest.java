package com.mycompany.paymybuddy;

import com.mycompany.paymybuddy.controller.ExternalTransferController;
import com.mycompany.paymybuddy.dto.BankAccountDTO;
import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.service.BankAccountService;
import com.mycompany.paymybuddy.service.TransferService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ExternalTransferControllerTest {
    @Autowired
    ExternalTransferController externalTransferController;
    @Mock
    BankAccountService bankAccountService;
    @Mock
    UserDetails userDetails;
    @Mock
    TransferService transferService;
    @Mock
    ExternalTransferDTO externalTransferDto;
    @Mock
    Model model;
    @Mock
    BankAccountDTO bankAccountDto;

    @Test
    void addBankAccount() {
//        when(bankAccountService.addBankAccount("emailTest", any())).thenReturn(any());
//        assertThrows(DataAlreadyExistException.class, () -> externalTransferController.addBankAccount(bankAccountDto, userDetails, redirectAttributes));
    }

    @Test
    void deleteBankAccount() {
        when(bankAccountService.delBankAccount("test")).thenReturn(true);
        String result = externalTransferController.deleteBankAccount("test");
        Assertions.assertThat(result).isEqualTo("redirect:/user/extransfer");
    }

    @Test
    void doExternalTransfer() {
//        when(transferService.doExternalTransfer(any())).thenReturn(new ExternalTransferDto());
//        String result = externalTransferController.doExternalTransfer(externalTransferDto, userDetails);
//        Assertions.assertThat(result).isEqualTo("redirect:/user/extransfer");
    }
}
