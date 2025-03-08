package com.dhag.praticien.api.praticien.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Adresse {

    @NotNull(message = "Le type de l'adresse est obligatoire")
    private AdresseType type;

    @NotBlank(message = "La rue de l'adresse est obligatoire")
    private String rue;

    private String complementRue;

    @NotBlank(message = "Le code postal de l'adresse est obligatoire")
    private String codePostal;

    @NotBlank(message = "La ville de l'adresse est obligatoire")
    private String ville;

    @NotBlank(message = "Le pays de l'adresse est obligatoire")
    private String pays;

    private boolean adressePrincipale = false;

    public enum AdresseType {
       OFFICE("Bureau"),
         HOME("Domicile"),
        OFFICIEL("Adresse officielle");

       private final String label;

       AdresseType(String label) {
           this.label = label;
       }

       public String getLabel() {
           return label;
       }
    }

    public @NotNull(message = "Le type de l'adresse est obligatoire") AdresseType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Le type de l'adresse est obligatoire") AdresseType type) {
        this.type = type;
    }

    public @NotBlank(message = "La rue de l'adresse est obligatoire") String getRue() {
        return rue;
    }

    public void setRue(@NotBlank(message = "La rue de l'adresse est obligatoire") String rue) {
        this.rue = rue;
    }

    public String getComplementRue() {
        return complementRue;
    }

    public void setComplementRue(String complementRue) {
        this.complementRue = complementRue;
    }

    public @NotBlank(message = "Le code postal de l'adresse est obligatoire") String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(@NotBlank(message = "Le code postal de l'adresse est obligatoire") String codePostal) {
        this.codePostal = codePostal;
    }

    public @NotBlank(message = "La ville de l'adresse est obligatoire") String getVille() {
        return ville;
    }

    public void setVille(@NotBlank(message = "La ville de l'adresse est obligatoire") String ville) {
        this.ville = ville;
    }

    public @NotBlank(message = "Le pays de l'adresse est obligatoire") String getPays() {
        return pays;
    }

    public void setPays(@NotBlank(message = "Le pays de l'adresse est obligatoire") String pays) {
        this.pays = pays;
    }

    public boolean isAdressePrincipale() {
        return adressePrincipale;
    }

    public void setAdressePrincipale(boolean adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }
}
