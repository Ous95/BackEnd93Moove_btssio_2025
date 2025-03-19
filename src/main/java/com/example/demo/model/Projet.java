package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
//Annotation Table
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="projet_generator", sequenceName = "projet_id_seq")
    private int id;
    @Column
    private String nom;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="id_tatoueur")
    private Tatoueur tatoueur;
    @ManyToOne
    @JoinColumn(name="id_tatoueura")
    private Tatoueura tatoueura;
    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name="id_adherent")
    private Adherent adherent;



    public Projet() {
    }

    public Projet(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}