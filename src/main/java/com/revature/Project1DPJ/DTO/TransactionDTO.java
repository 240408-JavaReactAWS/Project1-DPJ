package com.revature.Project1DPJ.DTO;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.TransactionType;
import com.revature.Project1DPJ.models.UserModel;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDTO {
    private int transactionID;
    private UserDTO sender;
    private int senderAccountNumber;
    private UserDTO receiver;
    private int receiverAccountNumber;
    private double sentAmount;
    private TransactionType type;
    private Date date;
    private Time time;

    public TransactionDTO() {
    }

    public TransactionDTO(UserDTO sender, int senderAccountNumber, double sentAmount, TransactionType type) {
        this.sender = sender;
        this.senderAccountNumber = senderAccountNumber;
        this.sentAmount = sentAmount;
        this.type = type;
    }

    public TransactionDTO(UserDTO sender, int senderAccountNumber, double sentAmount, TransactionType type, Date date, Time time) {
        this.sender = sender;
        this.senderAccountNumber = senderAccountNumber;
        this.sentAmount = sentAmount;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public TransactionDTO(int transactionID, UserDTO sender, int senderAccountNumber, UserDTO receiver, int receiverAccountNumber, int sentAmount, TransactionType type) {
        this.transactionID = transactionID;
        this.sender = sender;
        this.senderAccountNumber = senderAccountNumber;
        this.receiver = receiver;
        this.receiverAccountNumber = receiverAccountNumber;
        this.sentAmount = sentAmount;
        this.type = type;
        this.date = Date.valueOf(LocalDate.now());
        this.time= Time.valueOf(LocalTime.now());
    }


    public TransactionDTO(int transactionID, UserDTO sender, int senderAccountNumber, UserDTO receiver, int receiverAccountNumber, int sentAmount, TransactionType type, Date date, Time time) {
        this.transactionID = transactionID;
        this.sender = sender;
        this.senderAccountNumber = senderAccountNumber;
        this.receiver = receiver;
        this.receiverAccountNumber = receiverAccountNumber;
        this.sentAmount = sentAmount;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    public int getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(double sentAmount) {
        this.sentAmount = sentAmount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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
}
