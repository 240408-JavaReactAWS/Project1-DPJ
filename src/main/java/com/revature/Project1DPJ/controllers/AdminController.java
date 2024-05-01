package com.revature.Project1DPJ.controllers;
import com.revature.Project1DPJ.DTO.AdminDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.exceptions.FailedPatchingException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.AccountStatus;
import com.revature.Project1DPJ.models.Admin;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.services.AccountService;
import com.revature.Project1DPJ.services.AdminService;
import com.revature.Project1DPJ.services.UserServices;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AdminController {

    private final AdminService adminService;
    private final UserServices userServices;
    private final AccountService accountService;

    @Autowired
    public AdminController(AdminService adminService, UserServices userServices, AccountService accountService) {
        this.adminService = adminService;
        this.userServices = userServices;
        this.accountService = accountService;
    }


    /*
     * As an admin, I should be able to view all accounts
     */
    @GetMapping("/accounts")
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
     * As an admin, I should be able to create my account
     */
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin =  this.adminService.createAdminAccount(admin);

        if (savedAdmin != null) return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /*
    * As an admin, I should be able to view all users/customers
    * */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAllUsers(),HttpStatus.OK);
    }

    /*
     * As an admin, I should be able to log in
     */
    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) {
        Admin returnedAdmin =  this.adminService.login(admin.getEmail(), admin.getPassword());

        if (returnedAdmin != null) return new ResponseEntity<>(returnedAdmin, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> viewAllAdmins() {
        List<AdminDTO> returnedAdmins = this.adminService.getAllAdmins();
        if (returnedAdmins != null) return new ResponseEntity<>(returnedAdmins, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * As an admin, I should be able to reset a user password account
     */
    @PatchMapping("/user/resetpassword/{id}")
    public ResponseEntity<Account> resetUserAccountPassword(@PathVariable int id, @RequestBody UserModel user) {

        String password = user.getPassword();
        boolean updated;
        try {
            updated = this.userServices.patchUserAccountPassword(id, password);
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
    @PatchMapping("/user/status/{id}")
    public ResponseEntity<UserModel> userAccountStatus(@PathVariable int id, @RequestBody UserModel user) {

        boolean status = this.userServices.patchUserStatus(id, user.getUserStatus());

        if (status) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /*
     * As an admin, I should be able to enable/disable an account
     */
    @PatchMapping("/account/status")
    public ResponseEntity<Account> updateAccountStatus(@RequestBody Account account) {
        int accountOwnerId = account.getAccountOwner().getId();
        AccountStatus accountStatus = account.getAccountStatus();
        boolean status = this.accountService.patchAccountStatus(accountOwnerId ,account.getAccountStatus());

        if (status) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
