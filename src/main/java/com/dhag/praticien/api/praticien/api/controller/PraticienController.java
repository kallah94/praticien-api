package com.dhag.praticien.api.praticien.api.controller;

import java.util.List;

import com.dhag.praticien.api.praticien.api.model.Adresse;
import com.dhag.praticien.api.praticien.api.model.Praticien;
import com.dhag.praticien.api.praticien.api.service.PraticienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/praticiens")
@Tag(name = "Praticiens", description = "API pour gérer les praticiens")
public class PraticienController {

    @Autowired
    private PraticienService praticienService;

    @GetMapping
    @Operation(summary = "Récupérer tous les praticiens", description = "Retourne la liste de tous les praticiens")
    public ResponseEntity<List<Praticien>> getAllPraticiens() {
        List<Praticien> praticiens = praticienService.findAll();
        return new ResponseEntity<>(praticiens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un praticien par ID", description = "Retourne un praticien spécifique par son ID")
    public ResponseEntity<Praticien> getPraticienById(@PathVariable String id) {
        Praticien praticien = praticienService.findById(id);
        return new ResponseEntity<>(praticien, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des praticiens par nom ou prénom", description = "Retourne les praticiens correspondant au nom ou prénom recherché")
    public ResponseEntity<List<Praticien>> searchPraticiens(@RequestParam String search) {
        List<Praticien> praticiens = praticienService.findByNomOrPrenom(search);
        return new ResponseEntity<>(praticiens, HttpStatus.OK);
    }

    @GetMapping("/specialite/{specialiteId}")
    @Operation(summary = "Rechercher des praticiens par spécialité", description = "Retourne les praticiens ayant une spécialité donnée")
    public ResponseEntity<List<Praticien>> getPraticiensBySpecialite(@PathVariable String specialiteId) {
        List<Praticien> praticiens = praticienService.findBySpecialite(specialiteId);
        return new ResponseEntity<>(praticiens, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau praticien", description = "Crée un nouveau praticien")
    public ResponseEntity<Praticien> createPraticien(@Valid @RequestBody Praticien praticien) {
        Praticien createdPraticien = praticienService.create(praticien);
        return new ResponseEntity<>(createdPraticien, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un praticien", description = "Met à jour les informations d'un praticien existant")
    public ResponseEntity<Praticien> updatePraticien(
            @PathVariable String id,
            @Valid @RequestBody Praticien praticien) {

        Praticien updatedPraticien = praticienService.update(id, praticien);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un praticien", description = "Supprime un praticien existant")
    public ResponseEntity<Void> deletePraticien(@PathVariable String id) {
        praticienService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{praticienId}/specialites/{specialiteId}")
    @Operation(summary = "Ajouter une spécialité à un praticien", description = "Ajoute une spécialité existante à un praticien")
    public ResponseEntity<Praticien> addSpecialiteToPraticien(
            @PathVariable String praticienId,
            @PathVariable String specialiteId) {

        Praticien updatedPraticien = praticienService.addSpecialite(praticienId, specialiteId);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }

    @DeleteMapping("/{praticienId}/specialites/{specialiteId}")
    @Operation(summary = "Supprimer une spécialité d'un praticien", description = "Supprime une spécialité d'un praticien")
    public ResponseEntity<Praticien> removeSpecialiteFromPraticien(
            @PathVariable String praticienId,
            @PathVariable String specialiteId) {

        Praticien updatedPraticien = praticienService.removeSpecialite(praticienId, specialiteId);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }

    @GetMapping("/{praticienId}/adresses")
    @Operation(summary = "Récupérer toutes les adresses d'un praticien", description = "Retourne toutes les adresses d'un praticien")
    public ResponseEntity<List<Adresse>> getPraticienAdresses(@PathVariable String praticienId) {
        Praticien praticien = praticienService.findById(praticienId);
        return new ResponseEntity<>(praticien.getAdresses(), HttpStatus.OK);
    }

    @GetMapping("/{praticienId}/adresses/type/{type}")
    @Operation(summary = "Récupérer les adresses d'un praticien par type", description = "Retourne les adresses d'un praticien filtré par type")
    public ResponseEntity<List<Adresse>> getPraticienAdressesByType(
            @PathVariable String praticienId,
            @PathVariable Adresse.AdresseType type) {

        List<Adresse> adresses = praticienService.getAdressesByType(praticienId, type);
        return new ResponseEntity<>(adresses, HttpStatus.OK);
    }

    @PostMapping("/{praticienId}/adresses")
    @Operation(summary = "Ajouter une adresse à un praticien", description = "Ajoute une nouvelle adresse à un praticien")
    public ResponseEntity<Praticien> addAdresseToPraticien(
            @PathVariable String praticienId,
            @Valid @RequestBody Adresse adresse) {

        Praticien updatedPraticien = praticienService.addAdresse(praticienId, adresse);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }

    @PutMapping("/{praticienId}/adresses/{index}")
    @Operation(summary = "Mettre à jour une adresse d'un praticien", description = "Met à jour une adresse existante d'un praticien")
    public ResponseEntity<Praticien> updatePraticienAdresse(
            @PathVariable String praticienId,
            @PathVariable int index,
            @Valid @RequestBody Adresse adresse) {

        Praticien updatedPraticien = praticienService.updateAdresse(praticienId, index, adresse);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }

    @DeleteMapping("/{praticienId}/adresses/{index}")
    @Operation(summary = "Supprimer une adresse d'un praticien", description = "Supprime une adresse existante d'un praticien")
    public ResponseEntity<Praticien> deletePraticienAdresse(
            @PathVariable String praticienId,
            @PathVariable int index) {

        Praticien updatedPraticien = praticienService.removeAdresse(praticienId, index);
        return new ResponseEntity<>(updatedPraticien, HttpStatus.OK);
    }
}
