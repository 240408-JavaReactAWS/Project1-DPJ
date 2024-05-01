package com.revature.Project1DPJ.DTO;

import com.revature.Project1DPJ.models.TransactionType;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDTO {
    private int id;
    private SRDTO sender;
    private SRDTO receiver;
    private double total;
    private TransactionType type;
    private Date date;
    private Time time;

    public TransactionDTO() {
    }

    public TransactionDTO( SRDTO sender, double total, TransactionType type) {
        this.sender = sender;
        this.total = total;
        this.type = type;
        this.date = Date.valueOf(LocalDate.now());
        this.time= Time.valueOf(LocalTime.now());
    }

    public TransactionDTO(int id, SRDTO sender, double total, TransactionType type, Date date, Time time) {
        this.id = id;
        this.sender = sender;
        this.total = total;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public TransactionDTO( SRDTO sender, SRDTO receiver, double total, TransactionType type) {
        this.sender = sender;
        this.receiver = receiver;
        this.total = total;
        this.type = type;
        this.date = Date.valueOf(LocalDate.now());
        this.time= Time.valueOf(LocalTime.now());
    }

    public TransactionDTO(int id, SRDTO sender, SRDTO receiver, double total, TransactionType type, Date date, Time time) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.total = total;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SRDTO getSender() {
        return sender;
    }

    public void setSender(SRDTO sender) {
        this.sender = sender;
    }

    public SRDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(SRDTO receiver) {
        this.receiver = receiver;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
