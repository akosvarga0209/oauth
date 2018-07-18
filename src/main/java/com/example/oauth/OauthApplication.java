package com.example.oauth;

import com.example.oauth.model.Role;
import com.example.oauth.model.User;
import com.example.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OauthApplication implements ApplicationRunner {

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//username:user/admin,pw:user,
		userRepo.save(userRepo.save(new User("user", "$2a$04$hzf9tPN5YYQ9PUofqZO8MO6/r263CTOzgP3xnGbPTzlYwpazyR/C.",20000,20, Arrays.asList(new Role("ROLE_USER")))));
		userRepo.save(userRepo.save(new User("admin", "$2a$04$hzf9tPN5YYQ9PUofqZO8MO6/r263CTOzgP3xnGbPTzlYwpazyR/C.",20000,20, Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN")))));
	}
}
