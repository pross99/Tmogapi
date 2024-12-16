package dev.peterross.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;


@Configuration
public class GoogleCloudConfig {
    
    @Value("${google.cloud.service-account-key-path}")
    private Resource serviceAccountKeyFile;

    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        try (InputStream inputStream = serviceAccountKeyFile.getInputStream()) {
            return GoogleCredentials.fromStream(inputStream)
            .createScoped("https://www.googleapis.com/auth/cloud-platform");
               }
    }
}
