package com.mycompany.paymybuddy.controller;

import com.mycompany.paymybuddy.exceptions.DataAlreadyExistException;
import com.mycompany.paymybuddy.exceptions.DataNotFindException;
import com.mycompany.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")

public class RelationController {
    @Autowired
    private UserService userService;

    @GetMapping("/relation")
    public String relationPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("relations",userService.listEmailsRelation(userDetails.getUsername()));
        return "relation";
    }

    @PostMapping("/relation/addBuddy")
    public String addRelation(@RequestParam String emailBuddy, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        try {
            userService.addBuddy(userDetails.getUsername(), emailBuddy);
        }
        // Pour ameliorer l'affichage des erreurs dans la fenêtre
        catch (DataAlreadyExistException| DataNotFindException erreur){
            redirectAttributes.addFlashAttribute("errors", List.of(erreur.getMessage()));

        }
        return "redirect:/user/relation";
    }

    @PostMapping ("/relation")
    public String deleteRelation(@RequestParam Integer id){
        userService.delRelation(id);
        return "redirect:/user/relation";
    }


}
