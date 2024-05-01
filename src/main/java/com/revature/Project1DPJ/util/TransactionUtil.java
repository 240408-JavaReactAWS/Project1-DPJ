package com.revature.Project1DPJ.util;

import com.revature.Project1DPJ.DTO.SRDTO;
import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Transaction;

public class TransactionUtil {

    public static TransactionDTO mapTransactionWDToTransactionDTO(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        UserDTO user = UserUtil.mapUserToUserDTO(transaction.getAccount().getAccountOwner());
        SRDTO sender = UserUtil.mapUserToSRDTO(transaction.getId(),transaction.getAccount().getAccountNumber(),user);
        transactionDTO.setSender(sender);
        transactionDTO.setType(transaction.getTransactionType());
        transactionDTO.setTotal(transaction.getTotal());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setTime(transaction.getTime());
        return transactionDTO;
    }

    public static TransactionDTO mapTransactionSRToTransactionDTO(UserDTO rs, Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        UserDTO user = UserUtil.mapUserToUserDTO(transaction.getAccount().getAccountOwner());
        SRDTO sender = UserUtil.mapUserToSRDTO(transaction.getId(),transaction.getAccount().getAccountNumber(),user);
        transactionDTO.setSender(sender);
        SRDTO rsDTO=new SRDTO(transaction.getId(),transaction.getSra(),rs);
        transactionDTO.setReceiver(rsDTO);
        transactionDTO.setType(transaction.getTransactionType());
        transactionDTO.setTotal(transaction.getTotal());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setTime(transaction.getTime());
        return transactionDTO;
    }


    public static Transaction mapTransactionDTOToTransactionWD(Account account,TransactionDTO transactionDTO){
        Transaction transaction= new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAccount(account);
        transaction.setDate(transactionDTO.getDate());
        transaction.setTime(transactionDTO.getTime());
        transaction.setTotal(transactionDTO.getTotal());
        transaction.setTransactionType(transactionDTO.getType());

        return transaction;
    }

    public static Transaction mapTransactionDTOToTransactionSR(Account sender, int receiverId, int receiverAN,TransactionDTO transactionDTO){
        Transaction transaction= new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAccount(sender);
        transaction.setDate(transactionDTO.getDate());
        transaction.setTime(transactionDTO.getTime());
        transaction.setTotal(transactionDTO.getTotal());
        transaction.setTransactionType(transactionDTO.getType());
        transaction.setSr(receiverId);
        transaction.setSra(receiverAN);
        return transaction;
    }
}
