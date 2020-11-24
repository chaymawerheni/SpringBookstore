package com.vermeg.ams.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.ams.bookstore.entities.Commande;



@Repository
public interface CommandeRepository  extends CrudRepository<Commande, Long> {

}
