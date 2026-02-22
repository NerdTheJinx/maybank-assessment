package org.itsjinxed.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AssessmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssessmentApplication.class, args);
    }
}