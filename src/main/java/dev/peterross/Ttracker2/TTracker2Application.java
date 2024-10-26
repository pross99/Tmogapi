package dev.peterross.Ttracker2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {
    "dev.peterross.Ttracker2",
    "dev.peterross.Ttracker2.Security",
    "dev.peterross.Ttracker2.Controllers",
    "dev.peterross.Ttracker2.Repositories",
    "dev.peterross.Ttracker2.Services",
    "dev.peterross.Ttracker2.Entities",
    "dev.peterross.Ttracker2.payload"
})
@EnableWebSecurity
@EnableMongoRepositories


public class TTracker2Application {

	public static void main(String[] args) {
		SpringApplication.run(TTracker2Application.class, args);
	}


	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}