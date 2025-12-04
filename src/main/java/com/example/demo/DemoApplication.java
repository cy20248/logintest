package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] allBeanNames = appContext.getBeanDefinitionNames();
		for (String beanName : allBeanNames) {
			log.info("Bean名：{}", beanName);
		}
	}

	@Bean
	CommandLineRunner initUsers(UserRepository userRepository, BCryptPasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("password")); // 平文 password をハッシュ化
				admin.setRole("ADMIN");
				userRepository.save(admin);
			}

			if (userRepository.findByUsername("user").isEmpty()) {
				User user = new User();
				user.setUsername("user");
				user.setPassword(encoder.encode("password")); // 平文 password をハッシュ化
				user.setRole("USER");
				userRepository.save(user);
			}
		};
	}

}
