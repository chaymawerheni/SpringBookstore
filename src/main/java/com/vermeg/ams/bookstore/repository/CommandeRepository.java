package com.vermeg.ams.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.vermeg.ams.bookstore.entities.Commande;



@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{
}
