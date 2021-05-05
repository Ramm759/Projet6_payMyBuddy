package com.mycompany.paymybuddy;

import com.mycompany.paymybuddy.dao.UserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaymybuddyApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =  SpringApplication.run(PaymybuddyApplication.class, args);

		UserDAO userDAO = context.getBean(UserDAO.class);
		System.out.println(userDAO.findAll().get(0).getFirstname());

	}

}
