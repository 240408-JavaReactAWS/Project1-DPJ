package com.revature.Project1DPJ.util;

import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.exceptions.InsufficientFundsException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Transaction;

import java.sql.Date;

public class TransactionUtil {


    public static TransactionDTO mapTransactionToTransactionDTO(UserDTO sender, int sendersAN, UserDTO receiver, int receiversAN, Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(transaction.getTransactionId());
        transactionDTO.setSender(sender);
        transactionDTO.setSenderAccountNumber(sendersAN);
        transactionDTO.setReceiver(receiver);
        transactionDTO.setReceiverAccountNumber(receiversAN);
        transactionDTO.setType(transaction.getTransactionType());
        transactionDTO.setSentAmount(transaction.getTotal());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setTime(transaction.getTime());
        return transactionDTO;
    }

    public static TransactionDTO mapTransactionToTransactionDTO(UserDTO sender, int sendersAN, Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(transaction.getTransactionId());
        transactionDTO.setSender(sender);
        transactionDTO.setSenderAccountNumber(sendersAN);
        transactionDTO.setType(transaction.getTransactionType());
        transactionDTO.setSentAmount(transaction.getTotal());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setTime(transaction.getTime());
        return transactionDTO;
    }

    public static Transaction mapTransactionDTOToTransaction(Account account, TransactionDTO transactionDTO){
        Transaction transaction= new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionID());
        transaction.setAccount(account);
        transaction.setDate(transactionDTO.getDate());
        transaction.setTime(transactionDTO.getTime());
        transaction.setTotal(transactionDTO.getSentAmount());
        transaction.setTransactionType(transactionDTO.getType());
        return transaction;
    }
}
