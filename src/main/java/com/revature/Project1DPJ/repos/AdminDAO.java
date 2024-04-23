package com.revature.Project1DPJ.repos;
import com.revature.Project1DPJ.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {
}
