package com.revature.Project1DPJ.controllers;


import com.revature.Project1DPJ.exceptions.FailedPatchingException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.services.AccountService;
import com.revature.Project1DPJ.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {


    private AccountService accountService;
    private UserServices userService;

    @Autowired
    public AccountController(AccountService accountService, UserServices userServices) {
        this.accountService = accountService;
        this.userService = userServices;
    }


    /*
     * As an admin, I should be able to view all accounts
     */
    @PostMapping
    public ResponseEntity<Account>createAccount(@RequestBody Account account){
        Account savedAccount=accountService.saveAccount(account);
        if(savedAccount != null ){
          return   new ResponseEntity<Account>(savedAccount,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @GetMapping
    public ResponseEntity<List<Account>> viewAllAccounts() {
        List<Account> returnedAccounts;

        try {
            returnedAccounts = this.accountService.getAllAccounts();
        }
        catch (Exception e) {
            return new ResponseEntity<List<Account>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Account>>(returnedAccounts, HttpStatus.OK);
    }

    /*
     * As an admin, I should be able to view a user account
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account returnedAccount;
        try {
            returnedAccount = this.accountService.getUserAccountById(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnedAccount, HttpStatus.OK);
    }


    /*
     * As an admin, I should be able to reset a user password account
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Account> resetUserAccountPassword(@PathVariable int id, @RequestBody User user) {

        String password = user.getPassword();
        boolean updated;
        try {
            updated = this.userService.patchUserAccountPassword(id, password);
            if (updated) throw new FailedPatchingException("Failed to reset the user account password");
        }
        catch(FailedPatchingException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
     * As an admin, I should be able to lock/unlock a user account
     */
    @PatchMapping("/status")
    public ResponseEntity<Account> userAccountStatus(@RequestBody User user) {
        boolean status = user.isAccountStatus();
        user.setAccountStatus(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteUserAccount(@PathVariable int id) {
        boolean isDeleted;
        try{
            isDeleted = this.accountService.deleteUserAccountById(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
