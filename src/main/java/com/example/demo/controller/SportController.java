package com.example.demo.controller;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;
import com.example.demo.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@CrossOrigin
public class SportController {
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private SportService sportService;


    @GetMapping("/sport")
    public List<Sport> getSports(@RequestParam(required = false) String horaire) {
        return sportService.getSports(horaire);
    }

    @GetMapping("/sport/{id}")
    public Sport afficherSport(@PathVariable int id) throws NotFoundException {
        return this.sportRepository.findById(id).orElseThrow(() -> new NotFoundException("Sport non trouvé"));
    }



    @PostMapping("/sport")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Sport ajouterSport(@RequestBody Sport nouveauSport) {
        return sportService.ajouterSport(nouveauSport);
    }

    @PutMapping("/sport/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Sport modifierSport(@RequestBody Sport sportAModif, @PathVariable int id)
            throws NotFoundException{
        Sport s = this.sportRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Sport non trouvé"));
        s.setNom(sportAModif.getNom());
        s.setHoraire(sportAModif.getHoraire());
        return this.sportRepository.save(s);
    }


    @DeleteMapping("/sport/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerSport(@PathVariable int id) throws NotFoundException{
        Sport s = this.sportRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Sport non trouvé"));
        this.sportRepository.delete(s);
    }

}

