package com.dhag.praticien.api.praticien.api.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "specialites")
public class Specialite {

    @Id
    private String id;

    @NotBlank(message = "Le nom de la spécialité est obligatoire")
    private String nom;

    private String description;

    private Date dateCreation = new Date();

    private Date dateModification;

    public String getId() {
        return id;
    }


    public @NotBlank(message = "Le nom de la spécialité est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom de la spécialité est obligatoire") String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
