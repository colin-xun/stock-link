package com.fighting.stocklink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fighting.stocklink")
@MapperScan("com.fighting.stocklink.dao")
public class StockLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockLinkApplication.class, args);
	}

}
