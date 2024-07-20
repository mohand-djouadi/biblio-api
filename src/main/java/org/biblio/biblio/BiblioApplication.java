package org.biblio.biblio;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.biblio.biblio.initBDD;

@SpringBootApplication
@AllArgsConstructor
public class BiblioApplication implements CommandLineRunner {

	private initBDD initBDD;

	public static void main(String[] args) {
		SpringApplication.run(BiblioApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		initBDD.initializeDB();
	}

}
