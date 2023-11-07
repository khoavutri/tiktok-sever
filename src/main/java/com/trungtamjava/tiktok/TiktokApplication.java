package com.trungtamjava.tiktok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableCaching
public class TiktokApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiktokApplication.class, args);
	}

}
