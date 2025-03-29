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

    // Obtenir tous les adherents ou filtrer par prenom
    public List<Adherent> getAdherents(String prenom) {
        if (prenom != null) {
            return adherentRepository.findByPrenom(prenom);
        } else {
            return adherentRepository.findAll();
        }
    }

    // Ajouter un nouveau adherent
    public Adherent ajouterAdherent(Adherent nouveauAdherent) {
        return adherentRepository.save(nouveauAdherent);
    }

    //Afficher un adherent par ID
    public Adherent afficherAdherent(int id) throws NotFoundException {
        return adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Adherent non trouvé"));
    }

    // Modifier un adherent existant
    public Adherent modifierAdherent(int id, Adherent adherentAModif) throws NotFoundException {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Adherent non trouvé"));
        adherent.setNom(adherentAModif.getNom());
        adherent.setPrenom(adherentAModif.getPrenom());
        return adherentRepository.save(adherent);
    }

    // Supprimer un adherent
    public void supprimerAdherent(int id) throws NotFoundException {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Adherent non trouvé"));
        adherentRepository.delete(adherent);
    }
}