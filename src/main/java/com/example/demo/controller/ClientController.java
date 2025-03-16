package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;



    @GetMapping("/client")
    public List<Client> getClient(@RequestParam(required = false) String nom) {
        return clientService.getClient(nom);
    }

    @GetMapping("/client/{id}")
    public Client afficherClient(@PathVariable int id) {
        return this.clientRepository.findById(id);
    }


    //seulement l'admin peut ajouter un tatoueur
    @PostMapping("/client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client ajouterClient(@RequestBody Client nouveauClient) {
        return clientService.ajouterClient(nouveauClient);
    }

    @PutMapping("/client/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client modifierClient(@RequestBody Client clientAModif, @PathVariable int id)
            throws NotFoundException{
        Client c = this.clientRepository.findById(id);
        c.setNom(clientAModif.getNom());
        c.setTelephone(clientAModif.getTelephone());
        return this.clientRepository.save(c);
    }


    @DeleteMapping("/client/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerClient(@PathVariable int id){
        Client c = this.clientRepository.findById(id);
        this.clientRepository.delete(c);
    }
}
