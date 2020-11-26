package com.vermeg.ams.bookstore.controllers;

import javax.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.ams.bookstore.entities.Book;
import com.vermeg.ams.bookstore.repository.BookRepository;

import java.util.List;
import javax.validation.Valid;


@RequestMapping("/book/")

public class BookController {
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
    }

    @GetMapping("list")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "book/listeBooks";
		
    }

    @GetMapping("add")
    public String showAddBookForm(Book book) {
        return "book/addBook";
    }

    @PostMapping("add")
    //@ResponseBody
    public String addBook(@Valid Book book, BindingResult result,
			@RequestParam(name = "bookId", required = false) Long p,
			@RequestParam("files") MultipartFile[] files) {
       /* if (result.hasErrors()) {
            return "book/addBook";
        }
        //System.out.println(book);
        bookRepository.save(book);
        return "redirect:list";*/
    	// part upload
    			StringBuilder fileName = new StringBuilder();
    			MultipartFile file = files[0];
    			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    			fileName.append(file.getOriginalFilename());

    			try {
    				Files.write(fileNameAndPath, file.getBytes());
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			book.setPicture(fileName.toString());

    			bookRepository.save(book);
    			return "redirect:list";
    	
    }
  
    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book Id:" + id));
        bookRepository.delete(book);
        model.addAttribute("books", bookRepository.findAll());
        return "book/listeBooks";
    }
     
    @GetMapping("edit/{id}")
    public String showBookFormToUpdate(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "book/updateBook";
    }
    @PostMapping("update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result,Model model,
		@RequestParam(name = "bookId", required = false) Long p,
		@RequestParam("files") MultipartFile[] files) {

       /* if (result.hasErrors()) {
            book.setId(id);
            return "book/updateBook";
        }*/
	
    	StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());

		
		
		if(fileNameAndPath!=null) {
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			book.setPicture(fileName.toString());    
		}
		else {
			
			book.setPicture(book.getPicture()); 
		}
	
		 bookRepository.save(book);
	     model.addAttribute("books", bookRepository.findAll());
	
        return "book/listeBooks";
    }
    /*@GetMapping("show/{id}")
    public String showArticleDetails(@PathVariable("id") long id, Model model)
    {
    Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
    model.addAttribute("book", book);
    return "book/showBook";
    }*/


}
