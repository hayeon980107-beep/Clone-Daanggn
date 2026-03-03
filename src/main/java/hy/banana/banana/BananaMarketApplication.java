package hy.banana.banana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BananaMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BananaMarketApplication.class, args);
	}

}
