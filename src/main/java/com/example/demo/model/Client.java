package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;
//Annotation JPA entité
@Entity
//Annotation Table
@Table(name = "client")
public class Client {
    @Id
    //Annotation qui indique que la clé primaire est créée automatiquement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="client_generator", sequenceName = "client_id_seq")
    private int id;
    @Column
    private String nom;
    @Column
    private int telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Projet> projets;

    public Client(int id, String nom, int telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Client() {

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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }


}
