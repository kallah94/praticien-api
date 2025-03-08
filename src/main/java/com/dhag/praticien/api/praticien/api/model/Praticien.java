package com.dhag.praticien.api.praticien.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "praticiens")
public class Praticien {

    @Id
    private String id;

    @NotBlank(message = "Le nom du praticien est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom du praticien est obligatoire")
    private String prenom;

    @Email(message = "L'adresse email du praticien doit etre valide")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit être valide")
    private String telephone;


    private List<Adresse> adresses = new ArrayList<>();

    @DBRef
    private List<Specialite> specialites = new ArrayList<>();


    private Date dateCreation = new Date();


    private Date dateModification;


    private boolean actif = true;

    public String getId() {
        return id;
    }


    public @NotBlank(message = "Le nom du praticien est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom du praticien est obligatoire") String nom) {
        this.nom = nom;
    }

    public @NotBlank(message = "Le prénom du praticien est obligatoire") String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotBlank(message = "Le prénom du praticien est obligatoire") String prenom) {
        this.prenom = prenom;
    }

    public @Email(message = "L'adresse email du praticien doit etre valide") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "L'adresse email du praticien doit etre valide") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit être valide") String getTelephone() {
        return telephone;
    }

    public void setTelephone(@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit être valide") String telephone) {
        this.telephone = telephone;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public List<Specialite> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(List<Specialite> specialites) {
        this.specialites = specialites;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
