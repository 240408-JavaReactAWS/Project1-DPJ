package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.Transaction;
import com.revature.Project1DPJ.models.TransactionType;
import com.revature.Project1DPJ.services.AccountService;
import com.revature.Project1DPJ.services.TransactionService;
import com.revature.Project1DPJ.services.UserServices;
import com.revature.Project1DPJ.util.TransactionUtil;
import com.revature.Project1DPJ.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    UserServices userServices;

    public TransactionController() {
    }

    public TransactionController(TransactionService transactionService, AccountService accountService, UserServices userServices) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionDTO transaction){
        TransactionDTO savedTransaction;
        if(transaction.getType().equals(TransactionType.DEPOSIT) || transaction.getType().equals(TransactionType.WITHDRAWAL)){
            savedTransaction = transactionService.saveTransactionWD(transaction);
        }else{
            savedTransaction = transactionService.saveTransactionSR(transaction);
        }
        if(savedTransaction != null){
            return new ResponseEntity<>(savedTransaction,OK);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> getTransactionsByID(@PathVariable("id") int id){
        Optional<Transaction> usersTransaction = transactionService.getTransactionById(id);
        TransactionDTO transactionDTO;
        if(usersTransaction.isPresent()){
            if(usersTransaction.get().getTransactionType().equals(TransactionType.WITHDRAWAL)||usersTransaction.get().getTransactionType().equals(TransactionType.DEPOSIT)){
                transactionDTO=TransactionUtil.mapTransactionWDToTransactionDTO(usersTransaction.get());
                }else{
                UserDTO rs=(userServices.getUserById(usersTransaction.get().getSr()));
                transactionDTO=TransactionUtil.mapTransactionSRToTransactionDTO(rs,usersTransaction.get());
            }
            return new ResponseEntity<>(transactionDTO, OK);

        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(),OK);
    }



    @GetMapping("user/{id}")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByUserID(@PathVariable("id") int id){
        List<TransactionDTO> usersTransactions = (transactionService.getAllTransactionsByAccountId(id));
        if(usersTransactions != null){
          return new ResponseEntity<>(usersTransactions, OK);
        }
       return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("{id}/user/{userId}")
    public ResponseEntity<TransactionDTO> getTransactionByUserId(@PathVariable("id") int id, @PathVariable("userId")  int userId){
        Optional<Transaction> foundTransaction = transactionService.getTransactionById(id);
        TransactionDTO transactionDTO;
        if(foundTransaction.isPresent()){
            Transaction transaction= foundTransaction.get();

            if(transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)||transaction.getTransactionType().equals(TransactionType.DEPOSIT)){

                transactionDTO = TransactionUtil.mapTransactionWDToTransactionDTO(transaction);
            }else {
                   UserDTO userDTO = (userServices.getUserById(transaction.getSr()));
                    transactionDTO = TransactionUtil.mapTransactionSRToTransactionDTO(userDTO,transaction);

            }
                return new ResponseEntity<>(transactionDTO, OK);
        }
        return new ResponseEntity<>(NOT_FOUND);

    }

}
