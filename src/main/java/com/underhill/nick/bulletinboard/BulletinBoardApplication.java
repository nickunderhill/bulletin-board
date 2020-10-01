package com.underhill.nick.bulletinboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  BulletinBoardApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BulletinBoardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running");
	}
}
