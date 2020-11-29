package com.vermeg.ams.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.ams.bookstore.entities.DetailsCommande;


@Repository
public interface DetailsComRepository extends JpaRepository<DetailsCommande, Long>{
	
	
}
