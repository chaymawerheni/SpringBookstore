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
import com.vermeg.ams.bookstore.service.CommandeService;

//@Controller
@RequestMapping("/commande/")
public class CommandeController {

	private final CommandeRepository commandeRepository;
	private final BookRepository bookRepository;
	private final DetailsComRepository detailsComRepository;
	private final CommandeService commandeService ;

	@Autowired
	public CommandeController(CommandeRepository commandeRepository, BookRepository bookRepository,DetailsComRepository detailsComRepository, CommandeService commandeService ) {
		this.commandeService = commandeService;
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
		
		List <Book> books = null ;
		
		//creation d'une liste des livre
		
			for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
		
			books.add(book);
			}
		
		//calcul du prix totale
		/*
		 * for(long idB : lb) {
		 * 
		 * Book book = bookRepository.findById(idB).orElseThrow(() -> new
		 * IllegalArgumentException("Invalid book Id:" + idB));
		 * 
		 * if(book.getNbrstock()>1) {
		 * 
		 * prixTotale=prixTotale+book.getPrix(); } }
		*/
		
		
		commande.setPrix(commandeService.calculateTotalPrice(books));
		commandeRepository.save(commande);
		
		for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
			
			if(book.getNbrstock()>1) {
				
				DetailsCommande dc= new DetailsCommande(book.getPrix());
				dc.setBook(book);
				
				book.setNbrstock(book.getNbrstock()-1);
				dc.setPrixachat(book.getPrix());
				dc.setCommande(commande);
				
				detailsComRepository.save(dc);
		
			}
		
		} 
		return "redirect:list";
		
	}
	
	@GetMapping("delete/{id}")
	//@ResponseBody
    public String deleteCommande(@PathVariable("id") long id, Model model) {
		
		Commande commande = commandeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
		
		List<DetailsCommande> dc = detailsComRepository.findAllByCommandeId(commande.getId());
		
		for (DetailsCommande d : dc) {
				detailsComRepository.delete(d);		
		}
		
		commandeRepository.delete(commande);
		
		model.addAttribute("commandes", commandeRepository.findAll());
		return "commande/listCommandes";
    }
	
	 @GetMapping("show/{id}")
	 public String showCommandeDetails(@PathVariable("id") long id, Model model)
	{
		 
	 Commande commande = commandeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid book Id:" + id));

	 model.addAttribute("commande", commande);
	 model.addAttribute("detailscom", detailsComRepository.findAllByCommandeId(id));
	
	 return "commande/showCommande";
	 }
	
	 @GetMapping("edit/{id}")
    public String showCommandeFormToUpdate(@PathVariable("id") long id, Model model) {
		 
        Commande commande = commandeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid commande Id:" + id));
        model.addAttribute("commande", commande);
        model.addAttribute("books", bookRepository.findAll());
        
        return "commande/updateCommande";
    }

	 
	//@ResponseBody
	@PostMapping("update/{id}")
	 public String updateCommande(@PathVariable("id") long id, @Valid Commande commande, BindingResult result,
	 Model model, @RequestParam(name = "bookId", required = false) List<Long> lb) {
	 
		if (result.hasErrors()) {
	 commande.setId(id);
	 return "commande/updateCommande";
	 }	
		//suppression des details commandes
		List<DetailsCommande> dcom = detailsComRepository.findAllByCommandeId(commande.getId());
		
		//avant de supprimer les lignes des commandes on incremente le nbr de stock des lives 
		//selectionnee avant la modif pour qu'on peut modifier les livres
		
		for (DetailsCommande d : dcom) {
			
			Book book = bookRepository.findById(d.getBook().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + d.getBook().getId()));
			
			book.setNbrstock(d.getBook().getNbrstock()+1);
			bookRepository.save(book);
		}
		//suppression des details com
		for (DetailsCommande d : dcom) {
				detailsComRepository.delete(d);		
		}
		
		double prixTotale=0;

		//calcul du prix totale
		for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
		
			if(book.getNbrstock()>1) {
				
				prixTotale=prixTotale+book.getPrix();
			}		
		}
		
		commande.setPrix(prixTotale);
		commandeRepository.save(commande);
		//creations des nouveaux lignes apres la modifications
		for(long idB : lb) {
			
			Book book = bookRepository.findById(idB).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + idB));	
	
			if(book.getNbrstock()>1) {
				
				DetailsCommande dc= new DetailsCommande(book.getPrix());
				dc.setBook(book);
				
				book.setNbrstock(book.getNbrstock()-1);
				dc.setPrixachat(book.getPrix());
				dc.setCommande(commande);
				
				detailsComRepository.save(dc);
		
			}	
		
		}
		model.addAttribute("commandes", commandeRepository.findAll());
	 
	 return "commande/listCommandes";
	 }
	
}
