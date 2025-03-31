package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Culture;
import com.example.demo.repository.CultureRepository;
import com.example.demo.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.List;


//Nous aurons bien sûr dès le départ notre cultureController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class CultureController {
    @Autowired
    private CultureRepository cultureRepository;
    @Autowired
    private CultureService cultureService;



    @GetMapping("/culture")
    public List<Culture> getCultures(@RequestParam(required = false) String date) {
        return cultureService.getCultures(date);
    }

    @GetMapping("/culture/{id}")
    public Culture afficherCulture(@PathVariable int id) throws NotFoundException {
        return this.cultureRepository.findById(id).orElseThrow(() -> new NotFoundException("Culture non trouvé"));
    }



    //seulement l'admin peut ajouter un culture
    @PostMapping("/culture")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Culture ajouterCulture(@RequestBody Culture nouveauCulture) {
        return cultureService.ajouterCulture(nouveauCulture);
    }

    @PutMapping("/culture/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Culture modifierCulture(@RequestBody Culture cultureAModif, @PathVariable int id)
            throws NotFoundException{
        Culture c = this.cultureRepository.findById(id).orElseThrow(() -> new
                NotFoundException("culture non trouvé"));
        c.setNom(cultureAModif.getNom());
        c.setDate(cultureAModif.getDate());
        return this.cultureRepository.save(c);
    }


    @DeleteMapping("/culture/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerCulture(@PathVariable int id) throws NotFoundException{
        Culture a = this.cultureRepository.findById(id).orElseThrow(() -> new
                NotFoundException("culture non trouvé"));
        this.cultureRepository.delete(a);
    }

}

