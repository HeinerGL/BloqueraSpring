package com.project.bloquera;

import com.project.bloquera.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class BloqueraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloqueraApplication.class, args);
	}

}
