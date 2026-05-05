package com.loriek.crmloriek.config;

import com.loriek.crmloriek.model.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {



    @Bean
    public CommandLineRunner initData(UserService userService) {
        return args -> {

            if (userService.findByUsername("admin").isEmpty()) {
                userService.createUser("admin", "admin123");
                System.out.println("Default admin user created: admin / admin123");
            }
        };
    }
}
