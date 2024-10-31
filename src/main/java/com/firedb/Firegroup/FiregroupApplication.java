package com.firedb.Firegroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories
public class FiregroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiregroupApplication.class, args);
    }


}
