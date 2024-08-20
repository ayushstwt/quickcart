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
public class QuickCartApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {

		SpringApplication.run(QuickCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setRoleId(AppConstants.ADMIN_ID);
			adminRole.setRoleName("ADMIN");

			Role userRole = new Role();
			userRole.setRoleId(AppConstants.USER_ID);
			userRole.setRoleName("USER");

			List<Role> roles = List.of(adminRole, userRole);

			List<Role> savedRoles = roleRepository.saveAll(roles);

			savedRoles.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
