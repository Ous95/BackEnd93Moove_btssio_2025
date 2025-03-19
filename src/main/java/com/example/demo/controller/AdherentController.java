package com.example.demo.controller;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Adherent;
import com.example.demo.model.Tatoueura;
import com.example.demo.repository.AdherentRepository;
import com.example.demo.repository.TatoueuraRepository;
import com.example.demo.service.AdherentService;
import com.example.demo.service.TatoueuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


//Nous aurons bien sûr dès le départ notre TatoueurController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class AdherentController {
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private AdherentService adherentService;

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

    @GetMapping("/adherent")
    public List<Adherent> getAdherent(@RequestParam(required = false) String style) {
        return adherentService.getAdherent(style);
    }



    @GetMapping("/adherent/{id}")
    public Adherent afficherAdherent(@PathVariable int id) throws NotFoundException {
        return this.adherentRepository.findById(id).orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

   /* @PostMapping("/tatoueurs")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur){
        return this.tatoueurRepository.save(nouveauTatoueur);
    }
    */

    //seulement l'admin peut ajouter un tatoueur
    @PostMapping("/adherent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Adherent ajouterAdherent(@RequestBody Adherent nouveauAdherent) {
        return adherentService.ajouterAdherent(nouveauAdherent);
    }

    @PutMapping("/adherent/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Adherent modifierAdherent(@RequestBody Adherent adherentModif, @PathVariable int id)
            throws NotFoundException{
        Adherent a = this.adherentRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        a.setNom(adherentModif.getNom());
        a.setStyle(adherentModif.getStyle());
        return this.adherentRepository.save(a);
    }


    @DeleteMapping("/adherent/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerAdherent(@PathVariable int id) throws NotFoundException{
        Adherent a = this.adherentRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        this.adherentRepository.delete(a);
    }

}

