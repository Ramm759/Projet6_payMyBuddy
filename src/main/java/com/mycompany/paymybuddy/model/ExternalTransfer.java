package com.mycompany.paymybuddy.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "external_transfer")
@PrimaryKeyJoinColumn(name = "transfer_id")
public class ExternalTransfer extends Transfer{
    @JoinColumn (name = "bank_account_iban")
    @ManyToOne
    private BankAccount bankAccount;
    @Column (name = "fees")
    private BigDecimal fees;

    public ExternalTransfer() {
    }

    public ExternalTransfer(BankAccount bankAccount, BigDecimal fees) {
        this.bankAccount = bankAccount;
        this.fees = fees;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }
}
