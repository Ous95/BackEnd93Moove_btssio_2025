package com.example.demo.service;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Activite;
import com.example.demo.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepository activiteRepository;

    // Obtenir tous les activites ou filtrer par horaire
    public List<Activite> getActivites(String horaire) {
        if (horaire != null) {
            return activiteRepository.findByHoraire(horaire);
        } else {
            return activiteRepository.findAll();
        }
    }

    // Ajouter un nouveau activite
    public Activite ajouterActivite(Activite nouveauActivite) {
        return activiteRepository.save(nouveauActivite);
    }

    //Afficher un activite par ID
    public Activite afficherActivite(int id) throws NotFoundException {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
    }

    // Modifier un activite existant
    public Activite modifierActivite(int id, Activite activiteAModif) throws NotFoundException {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
        activite.setNom(activiteAModif.getNom());
        activite.setHoraire(activiteAModif.getHoraire());
        return activiteRepository.save(activite);
    }

    // Supprimer un activite
    public void supprimerActivite(int id) throws NotFoundException {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
        activiteRepository.delete(activite);
    }
}