package com.example.demo.controller;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Tatoueura;
import com.example.demo.repository.TatoueuraRepository;
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
public class TatoueuraController {
    @Autowired
    private TatoueuraRepository tatoueuraRepository;
    @Autowired
    private TatoueuraService tatoueuraService;

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

    @GetMapping("/tatoueura")
    public List<Tatoueura> getTatoueurs(@RequestParam(required = false) String style) {
        return tatoueuraService.getTatoueurs(style);
    }

    @GetMapping("/tatoueura/{id}")
    public Tatoueura afficherTatoueur(@PathVariable int id) throws NotFoundException {
        return this.tatoueuraRepository.findById(id).orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

   /* @PostMapping("/tatoueurs")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur){
        return this.tatoueurRepository.save(nouveauTatoueur);
    }
    */

    //seulement l'admin peut ajouter un tatoueur
    @PostMapping("/tatoueura")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueura ajouterTatoueura(@RequestBody Tatoueura nouveauTatoueura) {
        return tatoueuraService.ajouterTatoueura(nouveauTatoueura);
    }

    @PutMapping("/tatoueura/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueura modifierTatoueur(@RequestBody Tatoueura tatoueuraAModif, @PathVariable int id)
            throws NotFoundException{
        Tatoueura ta = this.tatoueuraRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        ta.setNom(tatoueuraAModif.getNom());
        ta.setStyle(tatoueuraAModif.getStyle());
        return this.tatoueuraRepository.save(ta);
    }


    @DeleteMapping("/tatoueura/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerTatoueura(@PathVariable int id) throws NotFoundException{
        Tatoueura ta = this.tatoueuraRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        this.tatoueuraRepository.delete(ta);
    }

}

