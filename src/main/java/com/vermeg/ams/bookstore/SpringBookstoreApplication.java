package com.vermeg.ams.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vermeg.ams.bookstore.controllers.BookController;
import java.io.File;

@SpringBootApplication
public class SpringBookstoreApplication {

	public static void main(String[] args) {
		new File(BookController.uploadDirectory).mkdir();
		SpringApplication.run(SpringBookstoreApplication.class, args);
	}

}
