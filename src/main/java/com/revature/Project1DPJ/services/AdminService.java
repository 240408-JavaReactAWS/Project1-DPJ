package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.repos.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public AdminService() {
    }

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
}
