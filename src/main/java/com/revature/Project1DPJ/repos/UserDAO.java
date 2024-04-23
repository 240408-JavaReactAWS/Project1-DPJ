package com.revature.Project1DPJ.repos;

import com.revature.Project1DPJ.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
}
