package com.example.daoauth;

import com.example.daoauth.entity.UserEntity;
import com.example.daoauth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DaoAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaoAuthApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepo) {
		return args -> {
			if (userRepo.findByUsername("admin").isEmpty()) {
				userRepo.save(new UserEntity(null, "admin", "admin123", "ADMIN"));
			}
			if (userRepo.findByUsername("user").isEmpty()) {
				userRepo.save(new UserEntity(null, "user", "user123", "USER"));
			}
		};
	}
}
