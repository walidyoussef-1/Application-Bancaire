package com.first.fst.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Compte {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String titulaire;
private double solde;

public Compte() {}
public Compte(int id, String titulaire, double solde) {
this.id = id;
this.titulaire = titulaire;
this.solde = solde;
}

// Getters et Setters
public int getId() { return id; }
public String getTitulaire() { return titulaire; }
public double getSolde() { return solde; }

public void setId(int id) { this.id = id; }
public void setTitulaire(String titulaire) { this.titulaire = titulaire; }
public void setSolde(double solde) { this.solde = solde; }

// Méthodes métier
public void deposer(double montant) {
if (montant > 0) this.solde += montant;
}

public void retirer(double montant) {
if (montant > 0 && montant <= this.solde) this.solde -= montant;
}
}
