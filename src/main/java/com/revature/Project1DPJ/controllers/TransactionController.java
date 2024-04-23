package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public TransactionController() {
    }

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
