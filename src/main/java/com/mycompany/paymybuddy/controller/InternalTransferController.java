package com.mycompany.paymybuddy.controller;

import com.mycompany.paymybuddy.dto.InternalTransferDTO;
import com.mycompany.paymybuddy.exceptions.DataNotExistException;
import com.mycompany.paymybuddy.exceptions.DataNotFindException;
import com.mycompany.paymybuddy.service.TransferService;
import com.mycompany.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class InternalTransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private UserService userService;

    @GetMapping("/internalTransfer")
    public String internalTransferPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("relations", userService.listEmailsRelation(userDetails.getUsername()));
        model.addAttribute("internalTransfer", new InternalTransferDTO()); // le Dto est rempli par l'utilisateur

        // Affichage liste
        model.addAttribute("transfers", transferService.listInternalTransfer(userDetails.getUsername()));




        return "internalTransfer";
    }

    @PostMapping("/internalTransfer/makeTransfer")
    public String internalTransfer(@ModelAttribute InternalTransferDTO internalTransferDTO, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        internalTransferDTO.setEmailSender(userDetails.getUsername());
        try {
            transferService.createInternalTransfer(internalTransferDTO);
        } catch (DataNotFindException | DataNotExistException | SQLException e) {
            redirectAttributes.addFlashAttribute("errors", List.of(e.getMessage()));
        }
        return "redirect:/user/internalTransfer";
    }
}
