package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@SpringBootApplication
@EnableTransactionManagement
public class WorkToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkToolsApplication.class, args);
	}

}
