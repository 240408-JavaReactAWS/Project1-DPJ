package com.revature.Project1DPJ.repos;

import com.revature.Project1DPJ.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findUserById(int userId);
    UserModel findUserByFirstName(String firstName);
}
