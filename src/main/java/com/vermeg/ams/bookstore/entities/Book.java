package com.vermeg.ams.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message="")
    @Column(name = "titre")
    private String titre;
    
    @NotBlank(message = "author is mandatory")
    @Column(name = "author")
    private String author;
    
    @NotBlank(message = "Prix is mandatory")
    @Column(name = "prix")
    private String prix;

    @NotBlank(message = "DateCreation is mandatory")
    @Column(name = "datecreation")
    private String datecreation;
    
    
    public Book() {}

	public Book(String titre, String author, String prix, String dateCreation) {
		
	
		this.titre = titre;
		this.author = author;
		this.prix = prix;
		this.datecreation = dateCreation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", titre=" + titre + ", author=" + author + ", prix=" + prix + ", datecreation="
				+ datecreation + "]";
	}



   
    
    
}

