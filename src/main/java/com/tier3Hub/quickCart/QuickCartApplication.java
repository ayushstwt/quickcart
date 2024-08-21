package com.tier3Hub.quickCart;

import com.tier3Hub.quickCart.entity.Role;
import com.tier3Hub.quickCart.repository.RoleRepository;
import com.tier3Hub.quickCart.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class QuickCartApplication {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {

		SpringApplication.run(QuickCartApplication.class, args);
	}

}
