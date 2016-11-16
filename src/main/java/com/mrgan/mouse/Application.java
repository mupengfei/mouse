package com.mrgan.mouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {
	private static Logger logger = LogManager.getLogger(Application.class
			.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(1314);
	}
}
