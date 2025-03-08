package com.dhag.praticien.api.praticien.api.controller;

import java.util.List;

import com.dhag.praticien.api.praticien.api.model.Specialite;
import com.dhag.praticien.api.praticien.api.service.SpecialiteService;
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
@RequestMapping("/api/specialites")
@Tag(name = "Specialite", description = "API pour les opérations CRUD sur les spécialités")
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping
    @Operation(summary = "Récupérer la liste des spécialités", description = "Récupérer la liste des spécialités")
    public ResponseEntity<List<Specialite>> getAllSpecialites() {
        List<Specialite> specialites = specialiteService.findAll();
        return new ResponseEntity<>(specialites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une spécialité par ID", description = "Retourne une spécialité médicale spécifique par son ID")
    public ResponseEntity<Specialite> getSpecialiteById(@PathVariable String id) {
        Specialite specialite = specialiteService.findById(id);
        return new ResponseEntity<>(specialite, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des spécialités par nom", description = "Retourne les spécialités médicales correspondant au nom recherché")
    public ResponseEntity<List<Specialite>> searchSpecialites(@RequestParam String nom) {
        List<Specialite> specialites = specialiteService.findByNom(nom);
        return new ResponseEntity<>(specialites, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle spécialité", description = "Crée une nouvelle spécialité médicale")
    public ResponseEntity<Specialite> createSpecialite(@Valid @RequestBody Specialite specialite) {
        Specialite createdSpecialite = specialiteService.create(specialite);
        return new ResponseEntity<>(createdSpecialite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une spécialité", description = "Met à jour les informations d'une spécialité médicale existante")
    public ResponseEntity<Specialite> updateSpecialite(
            @PathVariable String id,
            @Valid @RequestBody Specialite specialite) {

        Specialite updatedSpecialite = specialiteService.update(id, specialite);
        return new ResponseEntity<>(updatedSpecialite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une spécialité", description = "Supprime une spécialité médicale existante")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable String id) {
        specialiteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
