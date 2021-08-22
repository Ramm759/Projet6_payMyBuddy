package com.mycompany.paymybuddy.controller;

import com.mycompany.paymybuddy.dto.UserRegistrationDTO;
import com.mycompany.paymybuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")

public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showRegistrationForm() {
        return "registration";
    }

    @ModelAttribute("user") // le formulaire s'appelle user
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @PostMapping
    public String registerUserAccount (@ModelAttribute ("user") UserRegistrationDTO userRegistrationDTO){
        userService.save(userRegistrationDTO);
        return "redirect:/registration";
    }
}
