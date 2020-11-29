package com.vermeg.ams.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class DetailsCommande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "prixachat")
	private double prixachat;

	public DetailsCommande(double prixachat) {
		
		this.prixachat = prixachat;
	}

	public double getPrixachat() {
		return prixachat;
	}

	public void setPrixachat(double prixachat) {
		this.prixachat = prixachat;
	}

	/**** Many To One ****/
	/*
	 *
	 * FetchType.LAZY = Doesn’t load the relationships unless explicitly “asked for”
	 * via getter FetchType.EAGER = Loads ALL relationships
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "book_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	/**** Many To One ****/
	/*
	 *
	 * FetchType.LAZY = Doesn’t load the relationships unless explicitly “asked for”
	 * via getter FetchType.EAGER = Loads ALL relationships
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "commande_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Commande commande;

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	
	
	

}
