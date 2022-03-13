package dev.tahar.megamock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MegamockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegamockApplication.class, args);
	}

}
