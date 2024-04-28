package com.revature.Project1DPJ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Project1DpjApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project1DpjApplication.class, args);
	}

}
