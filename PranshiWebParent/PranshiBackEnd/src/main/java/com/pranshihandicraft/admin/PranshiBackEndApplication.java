package com.pranshihandicraft.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.common.pranshihandicraft.entity", "com.pranshihandicraft.admin.user"})
@SpringBootApplication
public class PranshiBackEndApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(PranshiBackEndApplication.class, args);
	}

}
