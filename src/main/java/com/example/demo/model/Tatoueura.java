package com.example.demo.model;
//Bibliothèque les bdd
import jakarta.persistence.*;

import java.util.List;

//Annotation JPA entité
@Entity
//Annotation Table
@Table(name = "Tatoueura")
public class Tatoueura {
    //Annotation qui indique que ce champs est la clé primaire
    @Id
//Annotation qui indique que la clé primaire est créée automatiquement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="tatoueura_generator", sequenceName = "tatoueura_id_seq")
    private int id;
    //Annotation qui indique que nom est un attribut de la table
    @Column
    private String nom;
    //Idem, attribut
    @Column
    private String style;
    @OneToMany(mappedBy = "tatoueura", cascade = CascadeType.ALL)
    private List<Projet> projets;


    //Constructeur vide obligatoire
    public Tatoueura(){
    }
    //2e constructeur
    public Tatoueura(int id, String nom, String style) {
        this.id = id;
        this.nom = nom;
        this.style=style;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getStyle() {
        return this.style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

////ajouter une liste de projets en attribut
//    @OneToMany(mappedBy = "tatoueur", cascade = CascadeType.ALL)
//    private List<Projet> projets;
}
