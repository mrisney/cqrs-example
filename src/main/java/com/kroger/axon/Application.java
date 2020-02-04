package com.kroger.axon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class)
					.logStartupInfo(false)
					.run(args);
	}
}
