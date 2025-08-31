package org.biblio.biblio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        FileSystemResource resource = new FileSystemResource(".env");
        configurer.setLocation(resource);

        try {
            Properties props = new Properties();
            props.load(resource.getInputStream());

            System.out.println("=== Variables chargées depuis .env ===");
            props.forEach((key, value) -> {
                System.out.println(key + " = " + value);
            });
            System.out.println("======================================");
        } catch (IOException e) {
            System.err.println("Impossible de charger le fichier .env : " + e.getMessage());
        }

        return configurer;
    }

}
