package com.mycompany.paymybuddy.model;

import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transfer")
// Pk sera partagée dans les classes filles
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Transfer {
    @Id
    // On dit à Hybernate que id est généré par la Bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column( name = "amount")
    private BigDecimal amount;

    @Column( name = "description")
    private String description;

    @Column( name = "transaction_date")
    private Date transactionDate;

    @Column( name = "status")
    private String status;

    public Transfer() {
    }

    public Transfer(BigDecimal amount, String description, Date transactionDate, String status) {
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public Transfer(Integer id, BigDecimal amount, String description, Date transactionDate, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
