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


//Nous aurons bien sûr dès le départ notre TatoueurController qui aura pour
//annotation que c’est une API Rest

@RestController
@CrossOrigin
public class ActiviteController {
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private ActiviteService activiteService;

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

    @GetMapping("/activite")
    public List<Activite> getActivites(@RequestParam(required = false) String style) {
        return activiteService.getActivites(style);
    }



    @GetMapping("/activite/{id}")
    public Activite afficherActivite(@PathVariable int id) throws NotFoundException {
        return this.activiteRepository.findById(id).orElseThrow(() -> new NotFoundException("Tatoueur non trouvé"));
    }

   /* @PostMapping("/tatoueurs")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur){
        return this.tatoueurRepository.save(nouveauTatoueur);
    }
    */

    //seulement l'admin peut ajouter un tatoueur
    @PostMapping("/activite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Activite ajouterActivite(@RequestBody Activite nouveauActivite) {
        return activiteService.ajouterActivite(nouveauActivite);
    }

    @PutMapping("/activite/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Activite modifierActivite(@RequestBody Activite activiteModif, @PathVariable int id)
            throws NotFoundException{
        Activite a = this.activiteRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        a.setNom(activiteModif.getNom());
        a.setStyle(activiteModif.getStyle());
        return this.activiteRepository.save(a);
    }


    @DeleteMapping("/activite/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerActivite(@PathVariable int id) throws NotFoundException{
        Activite a = this.activiteRepository.findById(id).orElseThrow(() -> new
                NotFoundException("Tatoueur non trouvé"));
        this.activiteRepository.delete(a);
    }

}

