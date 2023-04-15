package com.bank.vote.config;

import com.bank.vote.user.Role;
import com.bank.vote.user.User;
import com.bank.vote.user.UserRepository;
import com.bank.vote.voteitem.VoteItem;
import com.bank.vote.voteitem.VoteItemRepository;
import com.bank.vote.voterecord.VoteRecord;
import com.bank.vote.voterecord.VoteRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;

    @Bean
    @Transactional
    public UserDetailsService userDetailsService() {
        // Fetch UserDetail by using userRepository
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // A DAO, Fetch UserDetail and encode password
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Fetch User
        authProvider.setUserDetailsService(userDetailsService());
        // Password encoder
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Authenticate user base on username and password
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, VoteItemRepository voteItemRepository, VoteRecordRepository voteRecordRepository) {
        return args -> {
            User Leo = new User(
                    1,
                    "Leo",
                    "leo@mail.com",
                    passwordEncoder().encode("123456").toString(),
                    Role.USER
                    );
            User Sandy = new User(
                    2,
                    "Sandy",
                    "sandy@mail.com",
                    passwordEncoder().encode("123456"),
                    Role.USER
                    );
            User Randy = new User(
                    3,
                    "Randy",
                    "randy@mail.com",
                    passwordEncoder().encode("123456"),
                    Role.USER
                    );
            User RSY = new User(
                    4,
                    "RSY",
                    "rsy@mail.com",
                    passwordEncoder().encode("123456"),
                    Role.USER
                    );
            VoteItem item1 = new VoteItem(
                    1,
                    "電腦"
            );
            VoteItem item2 = new VoteItem(
                    2,
                    "滑鼠"
            );
            VoteRecord record1 = new VoteRecord(
                    1,
                    1,
                    "Leo",
                    1
            );
            VoteRecord record2 = new VoteRecord(
                    2,
                    2,
                    "Sandy",
                    1
            );
            VoteRecord record3 = new VoteRecord(
                    3,
                    2,
                    "Sandy",
                    2
            );
            VoteRecord record4 = new VoteRecord(
                    4,
                    2,
                    "Sandy",
                    3
            );
            VoteRecord record5 = new VoteRecord(
                    5,
                    3,
                    "Randy",
                    2
            );
            VoteRecord record6 = new VoteRecord(
                    6,
                    4,
                    "RSY",
                    2
            );

            userRepository.saveAll(
                    List.of(Leo, Sandy, Randy, RSY)
            );
            voteItemRepository.saveAll(
                    List.of(item1, item2)
            );
            voteRecordRepository.saveAll(
                    List.of(record1,
                            record2,
                            record3,
                            record4,
                            record5,
                            record6
                    )
            );
        };
    }

}
