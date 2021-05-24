package com.mycompany.paymybuddy.dto;

import java.math.BigDecimal;

public class InternalTransferDTO {
    private Integer id;
    private String emailReceiver;
    private BigDecimal amountUser;
    private String emailSender;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailReceiver() {
        return emailReceiver;
    }

    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public BigDecimal getAmountUser() {
        return amountUser;
    }

    public void setAmountUser(BigDecimal amountUser) {
        this.amountUser = amountUser;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
