package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WorkToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkToolsApplication.class, args);
	}

}
