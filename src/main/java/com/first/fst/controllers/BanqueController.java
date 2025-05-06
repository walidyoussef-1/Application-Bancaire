package com.first.fst.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.first.fst.entities.Compte;
import com.first.fst.repositories.CompteRepository;


@Controller
@RequestMapping("/Banque")
public class BanqueController {
private final CompteRepository compteRepository;

@Autowired
public BanqueController(CompteRepository compteRepository) {
this.compteRepository = compteRepository;
}

@GetMapping("/comptes")
public String afficher(Model model) {
model.addAttribute("comptes", compteRepository.findAll());
return "listeCompte";
}

@GetMapping("/details/{id}")
public String details(@PathVariable int id, Model model) {
Compte compte = compteRepository.findById(id).orElse(null);
if (compte == null) {
return "redirect:/Banque/comptes";
}
model.addAttribute("compte", compte);
return "detailsCompte";
}

@PostMapping("/depot/{id}")
public String depot(@PathVariable int id, @RequestParam double montant) {
Compte compte = compteRepository.findById(id).orElse(null);
if (compte != null) {
compte.deposer(montant);
compteRepository.save(compte);
}
return "redirect:/Banque/details/" + id;
}

@PostMapping("/retrait/{id}")
public String retrait(@PathVariable int id, @RequestParam double montant) {
Compte compte = compteRepository.findById(id).orElse(null);
if (compte != null && compte.getSolde() >= montant) {
compte.retirer(montant);
compteRepository.save(compte);
}
return "redirect:/Banque/details/" + id;
}

@GetMapping("/ajouter")
public String ajouter() {
return "ajouterCompte";
}

@PostMapping("/ajouter")
public String ajouterCompte(@ModelAttribute("compte") Compte c) {

compteRepository.save(c);
return "redirect:/Banque/comptes";
}


}