package com.revature.Project1DPJ.repos;
import com.revature.Project1DPJ.models.Admin;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {

    @Query("FROM Admin WHERE email = :emailVar AND password = :passwordVar")
    Admin findByUsernameAndPassword(@Param("emailVar") String email, @Param("passwordVar") String password);
}
