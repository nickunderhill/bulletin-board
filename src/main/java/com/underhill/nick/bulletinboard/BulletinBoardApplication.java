package com.underhill.nick.bulletinboard;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.RoleRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class  BulletinBoardApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BulletinBoardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running");
		User user = new User();
		user.setEmail("admin@email.com");
		user.setFirstName("Super");
		user.setLastName("Admin");
		user.setRoles(roleRepository.findAllByName("ADMIN"));
		user.setPassword(passwordEncoder.encode("123qwe"));
		userRepository.save(user);
	}
}
