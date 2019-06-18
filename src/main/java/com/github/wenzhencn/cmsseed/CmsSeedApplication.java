package com.github.wenzhencn.cmsseed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author zhen.wen
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class CmsSeedApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmsSeedApplication.class, args);
	}

}
