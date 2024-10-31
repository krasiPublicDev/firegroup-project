package com.firedb.Firegroup.config;

import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class ContactConfig {

    @Bean
    CommandLineRunner fillUser(ContactRepository repository) {
        return args -> {
            ContactEntity c = new ContactEntity(
                    "Sos",
                    LocalDate.of(2000, Month.JUNE, 30),
                    "bad knee");

            repository.save(c);
        };
    }
}
