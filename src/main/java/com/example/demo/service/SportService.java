package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;

    // Obtenir tous les tatoueurs ou filtrer par style
    public List<Sport> getSports(String style) {
        if (style != null) {
            return sportRepository.findByStyle(style);
        } else {
            return sportRepository.findAll();
        }
    }

    // Ajouter un nouveau tatoueur
    public Sport ajouterSport(Sport nouveauSport) {
        return sportRepository.save(nouveauSport);
    }

    //Afficher un tatoueur par ID
    public Sport afficherSport(int id) throws NotFoundException {
        return sportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sport non trouvé"));
    }

    // Modifier un tatoueur existant
    public Sport modifierSport(int id, Sport sportAModif) throws NotFoundException {
        Sport sport = sportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sport non trouvé"));
        sport.setNom(sportAModif.getNom());
        sport.setStyle(sportAModif.getStyle());
        return sportRepository.save(sport);
    }

    // Supprimer un tatoueur
    public void supprimerSport(int id) throws NotFoundException {
        Sport sport = sportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sport non trouvé"));
        sportRepository.delete(sport);
    }
}