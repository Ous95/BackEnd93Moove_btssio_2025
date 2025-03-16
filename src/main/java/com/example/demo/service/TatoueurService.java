package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Tatoueur;
import com.example.demo.repository.TatoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TatoueurService {
    @Autowired
    private TatoueurRepository tatoueurRepository;

    // Obtenir tous les tatoueurs ou filtrer par style
    public List<Tatoueur> getTatoueurs(String style) {
        if (style != null) {
            return tatoueurRepository.findByStyle(style);
        } else {
            return tatoueurRepository.findAll();
        }
    }

    // Ajouter un nouveau tatoueur
    public Tatoueur ajouterTatoueur(Tatoueur nouveauTatoueur) {
        return tatoueurRepository.save(nouveauTatoueur);
    }

    //Afficher un tatoueur par ID
    public Tatoueur afficherTatoueur(int id) throws NotFoundException {
        return tatoueurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

    // Modifier un tatoueur existant
    public Tatoueur modifierTatoueur(int id, Tatoueur tatoueurAModif) throws NotFoundException {
        Tatoueur tatoueur = tatoueurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        tatoueur.setNom(tatoueurAModif.getNom());
        tatoueur.setStyle(tatoueurAModif.getStyle());
        return tatoueurRepository.save(tatoueur);
    }

    // Supprimer un tatoueur
    public void supprimerTatoueur(int id) throws NotFoundException {
        Tatoueur tatoueur = tatoueurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        tatoueurRepository.delete(tatoueur);
    }
}