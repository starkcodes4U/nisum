package com.example.greeter;

import com.example.greeter.service.Greeter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Greeter greeter = context.getBean(Greeter.class);
		System.out.println(greeter.greet("World"));
	}
}