package com.rock.multibets.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rock.multibets.Service","com.rock.multibets.web"})
@EnableJpaRepositories("com.rock.multibets.repository")
@EntityScan("com.rock.multibets.domain")
public class MultibetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultibetsApplication.class, args);
	}
}
