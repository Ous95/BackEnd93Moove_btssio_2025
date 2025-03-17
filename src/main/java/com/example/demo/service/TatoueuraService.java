package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Tatoueura;
import com.example.demo.repository.TatoueuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TatoueuraService {
    @Autowired
    private TatoueuraRepository tatoueuraRepository;

    // Obtenir tous les tatoueurs ou filtrer par style
    public List<Tatoueura> getTatoueurs(String style) {
        if (style != null) {
            return tatoueuraRepository.findByStyle(style);
        } else {
            return tatoueuraRepository.findAll();
        }
    }

    // Ajouter un nouveau tatoueur
    public Tatoueura ajouterTatoueura(Tatoueura nouveauTatoueura) {
        return tatoueuraRepository.save(nouveauTatoueura);
    }

    //Afficher un tatoueur par ID
    public Tatoueura afficherTatoueura(int id) throws NotFoundException {
        return tatoueuraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

    // Modifier un tatoueur existant
    public Tatoueura modifierTatoueura(int id, Tatoueura tatoueuraAModif) throws NotFoundException {
        Tatoueura tatoueura = tatoueuraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        tatoueura.setNom(tatoueuraAModif.getNom());
        tatoueura.setStyle(tatoueuraAModif.getStyle());
        return tatoueuraRepository.save(tatoueura);
    }

    // Supprimer un tatoueur
    public void supprimerTatoueura(int id) throws NotFoundException {
        Tatoueura tatoueura = tatoueuraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
        tatoueuraRepository.delete(tatoueura);
    }
}