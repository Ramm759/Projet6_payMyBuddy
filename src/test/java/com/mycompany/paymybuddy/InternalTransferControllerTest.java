package com.mycompany.paymybuddy;

import com.mycompany.paymybuddy.controller.InternalTransferController;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;
import com.mycompany.paymybuddy.exceptions.DataNotFindException;
import com.mycompany.paymybuddy.model.Relation;
import com.mycompany.paymybuddy.service.TransferService;
import com.mycompany.paymybuddy.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.sql.SQLException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class InternalTransferControllerTest {

    @Autowired
    InternalTransferController internalTransferController;
    @Mock
    private TransferService transferService;
    @Mock
    private UserService userService;
    @Mock
    UserDetails userDetails;
    @Mock
    RedirectAttributes redirectAttributes;
    @Mock
    Model model;
    @Mock
    InternalTransferDTO internalTransferDTO;

    @Test
    void transferPage() {
        when(userService.listEmailsRelation("email1")).thenReturn(Arrays.asList(new Relation()));
        when(transferService.listInternalTransfer("email1")).thenReturn(Arrays.asList(new InternalTransferDTO()));
        String result = internalTransferController.transferPage(model, userDetails);
        Assertions.assertThat(result).isEqualTo("transfer2");
    }

    @Test
    void internalTransfer() throws SQLException {
        when(transferService.createInternalTransfer(internalTransferDTO)).thenReturn(internalTransferDTO);
        assertThrows(DataNotFindException.class, () -> internalTransferController.internalTransfer(internalTransferDTO, userDetails, redirectAttributes));
    }
}
