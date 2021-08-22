package com.mycompany.paymybuddy.controller;

import com.mycompany.paymybuddy.service.TransferService;
import com.mycompany.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class InternalTransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private UserService userService;

    @GetMapping ("/internalTransfer")

    public String internalTransferPage (){


        return "internalTransfer";
    }

}
