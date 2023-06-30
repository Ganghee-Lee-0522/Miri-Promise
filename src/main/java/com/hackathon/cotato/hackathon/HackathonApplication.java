package com.hackathon.cotato.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HackathonApplication {

	//@PostConstruct
	//public void started() {
		//TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	//}

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

}
