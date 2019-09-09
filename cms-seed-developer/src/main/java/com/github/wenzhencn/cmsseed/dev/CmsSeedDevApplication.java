package com.github.wenzhencn.cmsseed.dev;

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
public class CmsSeedDevApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmsSeedDevApplication.class, args);
	}

}
