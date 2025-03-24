package com.example.demo.controller;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;
import com.example.demo.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.List;


//Nous aurons bien sûr dès le départ notre SportController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class SportController {
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private SportService sportService;

    //une liste de Tatoueurs déjà définie « en dur »
    //private List<Tatoueur> listeTatoueurs = new ArrayList<>();

    //premier get
    /*@GetMapping("/tatoueurs")
    public List<Tatoueur> getTatoueurs() {
        return this.tatoueurRepository.findAll();
    }
*/
   /* @GetMapping("/tatoueurs")
    public List<Tatoueur> getTatoueurs(@RequestParam(required = false) String style) {
        if(style!=null){
            return this.tatoueurRepository.findByStyle(style);
        }else{
            return this.tatoueurRepository.findAll();
        }
    }
*/

    @GetMapping("/sport")
    public List<Sport> getSports(@RequestParam(required = false) String style) {
        return sportService.getSports(style);
    }

    @GetMapping("/sport/{id}")
    public Sport afficherSport(@PathVariable int id) throws NotFoundException {
        return this.sportRepository.findById(id).orElseThrow(() -> new NotFoundException("Sport non trouvé"));
    }

   /* @PostMapping("/tatoueurs")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur){
        return this.tatoueurRepository.save(nouveauTatoueur);
    }
    */

    //seulement l'admin peut ajouter un tatoueur
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
        s.setStyle(sportAModif.getStyle());
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

