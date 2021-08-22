package com.mycompany.paymybuddy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @Column(name = "iban", length = 34)
    private String iban;

    @Column(name = "bic")
    private String bic;

    @Column(name = "bank_name")
    private String bankname;

    @Column(name = "account_name")
    private String accountname;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "bankAccount")
    private List<ExternalTransfer> externalTransfers;

    public BankAccount() {
    }

    public BankAccount(String iban, String bic, String bankname, String accountname) {
        this.iban = iban;
        this.bic = bic;
        this.bankname = bankname;
        this.accountname = accountname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ExternalTransfer> getExternalTransfers() {
        return externalTransfers;
    }

    public void setExternalTransfers(List<ExternalTransfer> externalTransfers) {
        this.externalTransfers = externalTransfers;
    }
}
