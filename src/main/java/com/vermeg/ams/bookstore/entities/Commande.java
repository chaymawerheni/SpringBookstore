package com.vermeg.ams.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import com.vermeg.ams.bookstore.entities.Book;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "datecommande")
	private String datecommande;
	
	@Column(name = "prix")
	private double prix;
	
	@Column(name = "client")
	private String client;

	public Commande() {}

	public Commande(String datecommande, double prix, String client) {

		this.datecommande = datecommande;
		this.prix = prix;
		this.client=client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(String datecommande) {
		this.datecommande = datecommande;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", datecommande=" + datecommande + ", prix=" + prix + ", client=" + client + "]";
	}



	
	

	

	
}
