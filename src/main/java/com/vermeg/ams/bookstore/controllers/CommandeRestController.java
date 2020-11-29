package com.vermeg.ams.bookstore.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vermeg.ams.bookstore.entities.Commande;
import com.vermeg.ams.bookstore.repository.CommandeRepository;
import com.vermeg.ams.bookstore.repository.BookRepository;
import com.vermeg.ams.bookstore.exception.ResourceNotFoundException;
import java.util.List;
import javax.validation.Valid;

/*@RestController
@RequestMapping({"/commande"})
public class CommandeRestController {
	private final CommandeRepository commandeRepository;
	private final BookRepository bookRepository;
	@Autowired
	public CommandeRestController(CommandeRepository commandeRepository,
	BookRepository bookRepository) {
	this.commandeRepository = commandeRepository;
	this.bookRepository = bookRepository;
	}
	/*@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ProviderRepository providerRepository;
	@GetMapping("/list")
	public List<Commande> getAllCommandes() {
	return (List<Commande>) commandeRepository.findAll();
	}
	@PostMapping("/add/{bookId}")
	Commande createCommande(@PathVariable (value = "bookId") Long bookId,
	@Valid @RequestBody Commande commande) {

	return bookRepository.findById(bookId).map(book ->

	{

	commande.setBook(book);
	return commandeRepository.save(commande);
	}).orElseThrow(() -> new ResourceNotFoundException("BookId " + bookId + " not found"));

	}

	@PutMapping("/update/{bookId}/{commandeId}")
	public Commande updateCommande(@PathVariable (value = "bookId") Long
	bookId,
	@PathVariable (value = "commandeId") Long
	commandeId,
	@Valid @RequestBody Commande commandeRequest) {
	if(!bookRepository.existsById(bookId)) {
	throw new ResourceNotFoundException("BookId " + bookId + " not found");
	}
	return commandeRepository.findById(commandeId).map(commande -> {
	commande.setDatecommande(commandeRequest.getDatecommande());
	commande.setPrix(commandeRequest.getPrix());
		return commandeRepository.save(commande);
	}).orElseThrow(() -> new ResourceNotFoundException("CommandeId " +commandeId + "not found"));
	}
	@DeleteMapping("/delete/{commandeId}")
	public ResponseEntity<?> deleteCommande(@PathVariable (value = "commandeId")
	Long commandeId) {
	return commandeRepository.findById(commandeId).map(commande -> {
	commandeRepository.delete(commande);
	return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ResourceNotFoundException("Article not foundwith id " + commandeId));
	}
	
	}
*/
