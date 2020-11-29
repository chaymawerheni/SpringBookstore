package com.vermeg.ams.bookstore.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.vermeg.ams.bookstore.entities.Commande;
import com.vermeg.ams.bookstore.entities.DetailsCommande;
import com.vermeg.ams.bookstore.repository.BookRepository;
import com.vermeg.ams.bookstore.repository.CommandeRepository;
import com.vermeg.ams.bookstore.repository.DetailsComRepository;

@Controller
@RequestMapping("/commande/")
public class CommandeController {

	private final CommandeRepository commandeRepository;
	private final BookRepository bookRepository;
	private final DetailsComRepository detailsComRepository;

	@Autowired
	public CommandeController(CommandeRepository commandeRepository, BookRepository bookRepository, DetailsComRepository detailsComRepository) {
		this.commandeRepository = commandeRepository;
		this.bookRepository = bookRepository;
		this.detailsComRepository=detailsComRepository;
	}

	@GetMapping("list")
	// @ResponseBody
	public String listCommande(Model model) {
		// model.addAttribute("commandes", null);
		model.addAttribute("commandes", commandeRepository.findAll());

		return "commande/listCommandes";
	}

	@GetMapping("add")
	public String showAddCommandeForm(Commande commande, Model model) {
		
		model.addAttribute("books", bookRepository.findAll());

		return "commande/addCommande";
	}

	@PostMapping("add")
	//@ResponseBody
	public String addCommande(@Valid Commande commande, BindingResult result,
			@RequestParam(name = "bookId", required = false) List<Long> lb) {

		//Book book = bookRepository.findById(b).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + b));
		//commande.setBook(book);
		//System.out.println(lb);
		
		double prixTotale=0;
		
		for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
			prixTotale=prixTotale+book.getPrix();	
		}
		
		commande.setPrix(prixTotale);
		commandeRepository.save(commande);
		
		for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
			DetailsCommande dc= new DetailsCommande(book.getPrix());
			dc.setBook(book);
			book.setNbrstock(book.getNbrstock()-1);
			dc.setPrixachat(book.getPrix());
			dc.setCommande(commande);
			
			detailsComRepository.save(dc);
			
		}
		
		return "redirect:list";
		
	}
	
	/*@GetMapping("delete/{id}")
	public String deleteCommande(@PathVariable("id") long id, Model model) {
		Commande commande = commandeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
		
		List<DetailsCommande> ldc = new ArrayList<>();
		
		
		DetailsCommande dc= detailsComRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
		detailsComRepository.delete(dc);
		commandeRepository.delete(commande);
		model.addAttribute("commandes", commandeRepository.findAll());
		return "commande/listCommandes";
	}
	/* @GetMapping("edit/{id}")
    public String showCommandeFormToUpdate(@PathVariable("id") long id, Model model) {
        Commande commande = commandeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid commande Id:" + id));
        model.addAttribute("commande", commande);
        return "commande/updateCommande";
    }
    @PostMapping("update/{id}")
    public String updateCommande(@PathVariable("id") long id, @Valid Commande commande, BindingResult result,Model model,
		@RequestParam(name = "commandeId", required = false) Long p) {

       /* if (result.hasErrors()) {
            book.setId(id);
            return "book/updateBook";
        }
    	
          commandeRepository.save(commande);
	    model.addAttribute("commandes", commandeRepository.findAll());
	
        return "commande/listCommande";
    }  */
		
	//@ResponseBody
	@GetMapping("edit/{id}")
	 public String showCommandeFormToUpdate(@PathVariable("id") long id, Model model) {
	 
	
	 Commande commande = commandeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid book Id:" + id));
	 

	 model.addAttribute("commande", commande);
	 model.addAttribute("books", bookRepository.findAll());
	// model.addAttribute("idBook", commande.getBook().getId());

	 return "commande/updateCommande";
	 }
	
	//@ResponseBody
	@PostMapping("update/{id}")
	 public String updateCommande(@PathVariable("id") long id, @Valid Commande commande, BindingResult result,
	 Model model, @RequestParam(name = "bookId", required = false) Long p) {
	 
		if (result.hasErrors()) {
	 commande.setId(id);
	 return "commande/updateCommande";
	 }

	 Book book = bookRepository.findById(p).orElseThrow(()-> new IllegalArgumentException("Invalid book Id:" + p));
	 //commande.setBook(book);

	 commandeRepository.save(commande);
	 model.addAttribute("commandes", commandeRepository.findAll());
	 
	 return "commande/listCommandes";
	 }
	@GetMapping("show/{id}")
	 public String showCommandeDetails(@PathVariable("id") long id, Model model)
	{
	 Commande commande = commandeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid book Id:" + id));

	 model.addAttribute("commande", commande);

	 return "commande/showCommande";
	 }



	

}
