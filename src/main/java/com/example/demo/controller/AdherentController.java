package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Adherent;
import com.example.demo.repository.AdherentRepository;
import com.example.demo.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.List;


//Nous aurons bien sûr dès le départ notre AdherentController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class AdherentController {
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private AdherentService adherentService;



    @GetMapping("/adherent")
    public List<Adherent> getAdherents(@RequestParam(required = false) String horaire) {
        return adherentService.getAdherents(horaire);
    }

    @GetMapping("/adherent/{id}")
    public Adherent afficherAdherent(@PathVariable int id) throws NotFoundException {
        return this.adherentRepository.findById(id).orElseThrow(() -> new NotFoundException("Adherent non trouvé"));
    }



    //seulement l'admin peut ajouter un adherent
    @PostMapping("/adherent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Adherent ajouterAdherent(@RequestBody Adherent nouveauAdherent) {
        return adherentService.ajouterAdherent(nouveauAdherent);
    }

    @PutMapping("/adherent/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Adherent modifierAdherent(@RequestBody Adherent adherentAModif, @PathVariable int id)
            throws NotFoundException{
        Adherent c = this.adherentRepository.findById(id).orElseThrow(() -> new
                NotFoundException("adherent non trouvé"));
        c.setNom(adherentAModif.getNom());
        c.setHoraire(adherentAModif.getHoraire());
        return this.adherentRepository.save(c);
    }


    @DeleteMapping("/adherent/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerAdherent(@PathVariable int id) throws NotFoundException{
        Adherent a = this.adherentRepository.findById(id).orElseThrow(() -> new
                NotFoundException("adherent non trouvé"));
        this.adherentRepository.delete(a);
    }

}
