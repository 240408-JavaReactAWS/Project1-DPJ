package com.revature.Project1DPJ.repos;

import com.revature.Project1DPJ.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findUserById(int userId);

    @Query("FROM UserModel WHERE firstName = :firstNameVar")
    UserModel findUserByFirstName(@Param("firstNameVar") String firstName);

    @Query("FROM UserModel WHERE email = :emailVar")
    UserModel findUserByEmail(@Param("emailVar") String email);

    @Query("FROM UserModel WHERE email = :emailVar AND password = :passwordVar")
    UserModel findByEmailAndPassword(@Param("emailVar") String email, @Param("passwordVar") String password);
}
