package team.a9043.privacy_assessment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
public class PrivacyAssessmentSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrivacyAssessmentSystemApplication.class, args);
    }
}
