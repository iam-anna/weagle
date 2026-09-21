package com.weagle.config;

import com.weagle.entity.Role;
import com.weagle.entity.User;
import com.weagle.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner seedDemoUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createIfMissing(userRepository, passwordEncoder, "Operador", "operator@test.com", Role.OPERATOR);
            createIfMissing(userRepository, passwordEncoder, "Gestor", "manager@test.com", Role.MANAGER);
            createIfMissing(userRepository, passwordEncoder, "Líder", "leader@test.com", Role.LEADER);
        };
    }

    private void createIfMissing(
            UserRepository repository,
            PasswordEncoder encoder,
            String name,
            String email,
            Role role
    ) {
        if (!repository.existsByEmail(email)) {
            repository.save(User.builder()
                    .name(name)
                    .email(email)
                    .password(encoder.encode("123456"))
                    .role(role)
                    .build());
        }
    }
}
