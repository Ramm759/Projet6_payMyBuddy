package com.mycompany.paymybuddy.controller;

import com.mycompany.paymybuddy.dto.BankAccountDTO;
import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.exceptions.DataAlreadyExistException;
import com.mycompany.paymybuddy.exceptions.DataMissingException;
import com.mycompany.paymybuddy.model.BankAccount;
import com.mycompany.paymybuddy.service.BankAccountService;
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

import java.util.List;

@Controller
@RequestMapping("/user")
public class ExternalTransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping ("/externalTransfer")
    public String externalTransferPage (Model model, @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("bankAccount" , new BankAccountDTO());

        ExternalTransferDTO dto = new ExternalTransferDTO();
        model.addAttribute("externalTransfer",dto);

        model.addAttribute("bankAccount", new BankAccountDTO());

        // Affichage des bankAccounts
        List<BankAccount> bankAccounts = bankAccountService.listBankAccountByUser(userDetails.getUsername());
        model.addAttribute("listBankAccount", bankAccounts);

        // On chek si la liste des bankAccounts n'est pas vide, alors on rend le premier compte ajouté actif
        if (!bankAccounts.isEmpty()) {
            dto.setIbanUser(bankAccounts.get(bankAccounts.size()-1).getIban());
        }

        return "externalTransfer";
    }

    @PostMapping ("/externalTransfer/addBankAccount")
    public String addBankAccount(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes, BankAccountDTO bankAccountDTO){
        try {
            bankAccountService.addBankAccount(userDetails.getUsername(), bankAccountDTO);
        }
        catch (DataAlreadyExistException| DataMissingException e){
            redirectAttributes.addFlashAttribute("errors", List.of(e.getMessage()));
        }
        return "redirect:/user/externalTransfer";
    }

    @PostMapping ("/externalTransfer/makeTransfer")
    public String makeExternalTransfer (@ModelAttribute ExternalTransferDTO externalTransferDTO, @AuthenticationPrincipal UserDetails userDetails){
        externalTransferDTO.setEmailUser(userDetails.getUsername());
        transferService.createExternalTransfer(externalTransferDTO);
        return  "redirect:/user/externalTransfer";
    }
}
