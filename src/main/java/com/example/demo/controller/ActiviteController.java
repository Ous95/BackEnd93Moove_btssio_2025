package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Activite;
import com.example.demo.repository.ActiviteRepository;
import com.example.demo.service.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.List;


//Nous aurons bien sûr dès le départ notre activiteController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class ActiviteController {
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private ActiviteService activiteService;



    @GetMapping("/activite")
    public List<Activite> getActivites(@RequestParam(required = false) String horaire) {
        return activiteService.getActivites(horaire);
    }

    @GetMapping("/activite/{id}")
    public Activite afficherActivite(@PathVariable int id) throws NotFoundException {
        return this.activiteRepository.findById(id).orElseThrow(() -> new NotFoundException("Activite non trouvé"));
    }



    //seulement l'admin peut ajouter un activite
    @PostMapping("/activite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Activite ajouterActivite(@RequestBody Activite nouveauActivite) {
        return activiteService.ajouterActivite(nouveauActivite);
    }

    @PutMapping("/activite/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Activite modifierActivite(@RequestBody Activite activiteAModif, @PathVariable int id)
            throws NotFoundException{
        Activite a = this.activiteRepository.findById(id).orElseThrow(() -> new
                NotFoundException("activite non trouvé"));
        a.setNom(activiteAModif.getNom());
        a.setHoraire(activiteAModif.getHoraire());
        return this.activiteRepository.save(a);
    }


    @DeleteMapping("/activite/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerActivite(@PathVariable int id) throws NotFoundException{
        Activite a = this.activiteRepository.findById(id).orElseThrow(() -> new
                NotFoundException("activite non trouvé"));
        this.activiteRepository.delete(a);
    }

}


