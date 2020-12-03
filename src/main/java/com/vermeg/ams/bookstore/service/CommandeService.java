package com.vermeg.ams.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vermeg.ams.bookstore.entities.Book;
import com.vermeg.ams.bookstore.repository.BookRepository;



@Service("CommandeService")
public class CommandeService {
	
	
	public double calculateTotalPrice(List<Book> lb) {
		
			double totalPrice=0;
		
			for(Book b : lb) {
		
				totalPrice=totalPrice+b.getPrix();
						
		}
			return totalPrice;
		
	}
}
