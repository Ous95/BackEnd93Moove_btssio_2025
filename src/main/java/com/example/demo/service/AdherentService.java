package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Adherent;
import com.example.demo.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherentService {
    @Autowired
    private AdherentRepository adherentRepository;

    // Obtenir tous les tatoueurs ou filtrer par style
    public List<Adherent> getAdherent(String style) {
        if (style != null) {
            return adherentRepository.findByStyle(style);
        } else {
            return adherentRepository.findAll();
        }
    }

    // Ajouter un nouveau tatoueur
    public Adherent ajouterAdherent(Adherent nouveauAdherent) {
        return adherentRepository.save(nouveauAdherent);
    }

    //Afficher un tatoueur par ID
    public Adherent afficherAdherent(int id) throws NotFoundException {
        return adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

    // Modifier un tatoueur existant
    public Adherent modifierAdherent(int id, Adherent adherentModif) throws NotFoundException {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        adherent.setNom(adherentModif.getNom());
        adherent.setStyle(adherentModif.getStyle());
        return adherentRepository.save(adherent);
    }

    // Supprimer un tatoueur
    public void supprimerAdherent(int id) throws NotFoundException {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        adherentRepository.delete(adherent);
    }
}