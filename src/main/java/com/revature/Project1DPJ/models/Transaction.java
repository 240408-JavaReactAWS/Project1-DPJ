package com.revature.Project1DPJ.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    private int transactionId;
    @Column(nullable = false)
    private UserModel sender;
    @Column(nullable = false)
    private UserModel reciever;
    @Column(nullable = false)
    private double amountSent;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Time time;

    public Transaction() {
    }

    public Transaction(int transactionId, UserModel sender, UserModel reciever, double amountSent, Date date, Time time) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.reciever = reciever;
        this.amountSent = amountSent;
        this.date = date;
        this.time = time;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getReciever() {
        return reciever;
    }

    public void setReciever(UserModel reciever) {
        this.reciever = reciever;
    }

    public double getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(double amountSent) {
        this.amountSent = amountSent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", sender=" + sender +
                ", reciever=" + reciever +
                ", amountSent=" + amountSent +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
