package com.revature.Project1DPJ.controllers;
import com.revature.Project1DPJ.exceptions.FailedPatchingException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.services.AccountService;
import com.revature.Project1DPJ.services.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/admins")
public class AdminController {


    private AccountService accountService;
    private UserService userService;

    @Autowired
    public AdminController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    /*
     * As an admin, I should be able to view all accounts
     */
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

    @PatchMapping
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

    @PatchMapping
    public ResponseEntity<Account> userAccountStatus(@RequestBody User user) {
        boolean status = user.isAccountStatus();
        user.setAccountStatus(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
