package com.first.fst.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.first.fst.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte,Integer>{

}