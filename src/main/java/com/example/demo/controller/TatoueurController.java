package com.example.demo.controller;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Tatoueur;
import com.example.demo.repository.TatoueurRepository;
import com.example.demo.service.TatoueurService;
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
public class TatoueurController {
    @Autowired
    private TatoueurRepository tatoueurRepository;
    @Autowired
    private TatoueurService tatoueurService;

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

    @GetMapping("/tatoueur")
    public List<Tatoueur> getTatoueurs(@RequestParam(required = false) String style) {
        return tatoueurService.getTatoueurs(style);
    }

    @GetMapping("/tatoueur/{id}")
    public Tatoueur afficherTatoueur(@PathVariable int id) throws NotFoundException {
        return this.tatoueurRepository.findById(id).orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

   /* @PostMapping("/tatoueurs")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur){
        return this.tatoueurRepository.save(nouveauTatoueur);
    }
    */

    //seulement l'admin peut ajouter un tatoueur
    @PostMapping("/tatoueur")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur) {
        return tatoueurService.ajouterTatoueur(nouveauTatoueur);
    }

    @PutMapping("/tatoueur/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueur modifierTatoueur(@RequestBody Tatoueur tatoueurAModif, @PathVariable int id)
            throws NotFoundException{
        Tatoueur t = this.tatoueurRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        t.setNom(tatoueurAModif.getNom());
        t.setStyle(tatoueurAModif.getStyle());
        return this.tatoueurRepository.save(t);
    }


    @DeleteMapping("/tatoueur/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerTatoueur(@PathVariable int id) throws NotFoundException{
        Tatoueur t = this.tatoueurRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        this.tatoueurRepository.delete(t);
    }

}

