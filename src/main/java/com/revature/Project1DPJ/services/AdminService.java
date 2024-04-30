package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.DTO.AdminDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Admin;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.models.UserType;
import com.revature.Project1DPJ.repos.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


    public List<AdminDTO> getAllAdmins() {
        UserType type = UserType.ADMIN;
        List<AdminDTO> allAdminsDTO = new ArrayList<>();
        List<Admin> allUsers = this.adminDAO.findAll();

        if (!allUsers.isEmpty()) {
            List<Admin> allAdmins =  allUsers.stream()
                    .filter((user) -> user.getRole() == type)
                    .toList();
            for (Admin admin : allAdmins) {
                allAdminsDTO.add(this.mapAdminToAdminDTO(admin));
            }

        }
        return allAdminsDTO;
    }

    public Admin createAdminAccount(Admin admin) {
        return this.adminDAO.save(admin);
    }


    public Admin login(String email, String password) {
        return this.adminDAO.findByUsernameAndPassword(email, password);
    }

    public AdminDTO mapAdminToAdminDTO(Admin admin){
        return new AdminDTO(admin.getFirstName(), admin.getLastName(), admin.getEmail());
    }

//    public boolean resetUserAccountPassword(int id, String password) {
//        Optional<Account> optionalAccount = this.accountDAO.findById(id);
//        if (optionalAccount.isPresent()) {
//            Account account = optionalAccount.get();
//            UserModel userId = account.getAccountOwner();
//
//            Optional<User> optionalUser = this.userDAO.findById(id);
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                user.setPassword(password);
//                this.userDAO.save(user);
//                return this.userDAO.existsById(user.getId());
//            }
//            else {
//                return false;
//            }
//        }
//        return false;
//    }
}
