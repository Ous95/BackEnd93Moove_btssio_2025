package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClient(String nom) {
           return clientRepository.findAll();
       }


    // Ajouter un nouveau client
    public Client ajouterClient(Client nouveauClient) {
        return clientRepository.save(nouveauClient);
    }

    //Afficher un client par ID
    public Client afficherClient(int id) throws NotFoundException {
        return clientRepository.findById(id);
    }

    // Modifier un client existant
    public Client modifierClient(int id, Client clientAModif) throws NotFoundException {
        Client client = clientRepository.findById(id);
        client.setNom(clientAModif.getNom());
        client.setTelephone(clientAModif.getTelephone());
        return clientRepository.save(client);
    }

    // Supprimer un client
    public void supprimerClient(int id) throws NotFoundException {
        Client client = clientRepository.findById(id);
        clientRepository.delete(client);
    }
}