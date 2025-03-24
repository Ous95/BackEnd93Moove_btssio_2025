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

    // Obtenir tous les tatoueurs ou filtrer par style
    public List<Activite> getActivites(String style) {
        if (style != null) {
            return activiteRepository.findByStyle(style);
        } else {
            return activiteRepository.findAll();
        }
    }

    // Ajouter un nouveau tatoueur
    public Activite ajouterActivite(Activite nouveauActivite) {
        return activiteRepository.save(nouveauActivite);
    }

    //Afficher un tatoueur par ID
    public Activite afficherActivite(int id) throws NotFoundException {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
    }

    // Modifier un tatoueur existant
    public Activite modifierActivite(int id, Activite activiteAModif) throws NotFoundException {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
        activite.setNom(activiteAModif.getNom());
        activite.setStyle(activiteAModif.getStyle());
        return activiteRepository.save(activite);
    }

    // Supprimer un tatoueur
    public void supprimerActivite(int id) throws NotFoundException {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activite non trouvé"));
        activiteRepository.delete(activite);
    }
}