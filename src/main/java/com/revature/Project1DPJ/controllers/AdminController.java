package com.revature.Project1DPJ.controllers;
import com.revature.Project1DPJ.exceptions.FailedPatchingException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Admin;
import com.revature.Project1DPJ.models.User;
import com.revature.Project1DPJ.services.AccountService;
import com.revature.Project1DPJ.services.AdminService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
     * As an admin, I should be able to log in
     */
    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) {
        Admin returnedAdmin =  this.adminService.login(admin.getUsername(), admin.getPassword());

        if (returnedAdmin != null) return new ResponseEntity<>(returnedAdmin, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> viewAllAdmins() {
        List<Admin> returnedAdmins = this.adminService.getAllAdmins();
        if (returnedAdmins != null) return new ResponseEntity<>(returnedAdmins, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
