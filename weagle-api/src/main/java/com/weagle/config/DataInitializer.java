package com.weagle.config;

import com.weagle.entity.Role;
import com.weagle.entity.User;
import com.weagle.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            User anna = userRepository.findByEmail("anna@weagle.com")
                    .orElse(
                            User.builder()
                                    .name("Anna")
                                    .email("anna@weagle.com")
                                    .role(Role.OPERATOR)
                                    .build()
                    );
            anna.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(anna);


            User lider = userRepository.findByEmail("leader@weagle.com")
                    .orElse(
                            User.builder()
                                    .name("Leader")
                                    .email("leader@weagle.com")
                                    .role(Role.LEADER)
                                    .build()
                    );
            lider.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(lider);

            User manager = userRepository.findByEmail("manager@weagle.com")
                            .orElse(
                                    User.builder()
                                            .name("Manager")
                                            .email("manager@weagle.com")
                                            .role(Role.MANAGER)
                                            .build()
                            );
            manager.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(manager);

            System.out.println("========== USUÁRIOS CONFIGURADOS ==========");
            System.out.println("Anna: anna@weagle.com / OPERATOR");
            System.out.println("Leader: leader@weagle.com / LEADER");
            System.out.println("Manager: manager@weagle.com / MANAGER");
        };
    }
}