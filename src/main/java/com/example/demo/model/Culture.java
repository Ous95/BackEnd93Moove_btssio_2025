package com.example.demo.model;

//Bibliothèque les bdd
import jakarta.persistence.*;

import java.util.List;

//Annotation JPA entité
@Entity
//Annotation Table
@Table(name = "Culture")
public class Culture {
    //Annotation qui indique que ce champs est la clé primaire
    @Id
//Annotation qui indique que la clé primaire est créée automatiquement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="culture_generator", sequenceName = "culture_id_seq")
    private int id;
    //Annotation qui indique que nom est un attribut de la table
    @Column
    private String nom;
    //Idem, attribut
    @Column
    private String horaire;
    @OneToMany(mappedBy = "culture", cascade = CascadeType.ALL)
    private List<Projet> projets;


    //Constructeur vide obligatoire
    public Culture(){
    }
    //2e constructeur
    public Culture(int id, String nom, String horaire) {
        this.id = id;
        this.nom = nom;
        this.horaire=horaire;
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
    public String getHoraire() {
        return this.horaire;
    }
    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

////ajouter une liste de projets en attribut
//    @OneToMany(mappedBy = "Culture", cascade = CascadeType.ALL)
//    private List<Projet> projets;
}