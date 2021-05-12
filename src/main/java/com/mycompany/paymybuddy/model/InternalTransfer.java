package com.mycompany.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "internal_transfer")
@PrimaryKeyJoinColumn (name = "transfer_id")
public class InternalTransfer extends Transfer{
    @JoinColumn(name = "user_id_sender")
    @ManyToOne
    private User userSender;
    @JoinColumn(name = "user_id_receiver")
    @ManyToOne
    private User userReceiver;

    public InternalTransfer() {
    }

    public InternalTransfer(User userSender, User userReceiver) {
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }
}
