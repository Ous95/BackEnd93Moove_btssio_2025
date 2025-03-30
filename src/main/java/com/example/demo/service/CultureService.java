package com.example.demo.service;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Culture;
import com.example.demo.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureService {
    @Autowired
    private CultureRepository cultureRepository;

    // Obtenir tous les cultures ou filtrer par horaire
    public List<Culture> getCultures(String horaire) {
        if (horaire != null) {
            return cultureRepository.findByHoraire(horaire);
        } else {
            return cultureRepository.findAll();
        }
    }

    // Ajouter un nouveau culture
    public Culture ajouterCulture(Culture nouveauCulture) {
        return cultureRepository.save(nouveauCulture);
    }

    //Afficher un culture par ID
    public Culture afficherCulture(int id) throws NotFoundException {
        return cultureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Culture non trouvé"));
    }

    // Modifier un culture existant
    public Culture modifierCulture(int id, Culture cultureAModif) throws NotFoundException {
        Culture culture = cultureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Culture non trouvé"));
        culture.setNom(cultureAModif.getNom());
        culture.setHoraire(cultureAModif.getHoraire());
        return cultureRepository.save(culture);
    }

    // Supprimer un culture
    public void supprimerCulture(int id) throws NotFoundException {
        Culture culture = cultureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Culture non trouvé"));
        cultureRepository.delete(culture);
    }
}