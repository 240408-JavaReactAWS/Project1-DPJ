package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.repos.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    //hello

    @Autowired
    TransactionDAO transactionDAO;

    public TransactionService() {
    }

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
}
