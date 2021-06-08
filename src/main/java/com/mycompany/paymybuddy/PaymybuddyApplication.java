package com.mycompany.paymybuddy;

import com.mycompany.paymybuddy.dao.BankAccountDAO;
import com.mycompany.paymybuddy.dao.UserDAO;
import com.mycompany.paymybuddy.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class PaymybuddyApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =  SpringApplication.run(PaymybuddyApplication.class, args);

		UserDAO userDAO = context.getBean(UserDAO.class);
		System.out.println(userDAO.findAll().get(0).getFirstname());

		// Test liaison User/BankAccount
		List<User> users = userDAO.findAll();
		System.out.println("Iban 1 : " + users.get(0).getBankAccountList().get(0).getIban());

		// Test Jpa BankAccount
		BankAccountDAO bankAccountDAO = context.getBean(BankAccountDAO.class);
		System.out.println(bankAccountDAO.findAll().get(0).getIban());
	}
}
