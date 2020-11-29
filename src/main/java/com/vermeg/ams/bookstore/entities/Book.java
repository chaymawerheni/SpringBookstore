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

   
    @Column(name = "titre")
    private String titre;
    

    @Column(name = "author")
    private String author;
    
   
    @Column(name = "prix")
    private double prix;

    
    @Column(name = "datecreation")
    private String datecreation;
    
    @Column(name = "picture")
    private String picture;
    
    @Column(name = "nbrstock")
    private int  nbrstock;
    
    
    public Book() {}

	public Book(String titre, String author, double prix, String dateCreation, int nbrstock) {
		
	
		this.titre = titre;
		this.author = author;
		this.prix = prix;
		this.datecreation = dateCreation;
		this.nbrstock=nbrstock;
	}
	
	

	public int getNbrstock() {
		return nbrstock;
	}

	public void setNbrstock(int nbrstock) {
		this.nbrstock = nbrstock;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
		}

		public String getPicture() {
		return picture;
		}

		@Override
		public String toString() {
			return "Book [id=" + id + ", titre=" + titre + ", author=" + author + ", prix=" + prix + ", datecreation="
					+ datecreation + ", picture=" + picture + ", nbrstock=" + nbrstock + "]";
		}

  
    
}

