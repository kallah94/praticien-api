package com.dhag.praticien.api.praticien.api.service;

import java.util.Date;
import java.util.List;

import com.dhag.praticien.api.praticien.api.exception.ResourceNotFoundException;
import com.dhag.praticien.api.praticien.api.model.Specialite;
import com.dhag.praticien.api.praticien.api.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialiteService {
    @Autowired
    private SpecialiteRepository specialiteRepository;

    public List<Specialite> findAll() {
        return specialiteRepository.findAll();
    }

    public Specialite findById(String id) {
        return specialiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialite", "id", id));
    }

    public List<Specialite> findByNom(String nom) {
        return specialiteRepository.findByNomContaining(nom);
    }

    public Specialite create(Specialite specialite) {
        if (specialiteRepository.existsByNom(specialite.getNom())) {
            throw new IllegalArgumentException("La spécialité existe déjà");
        }
        specialite.setDateCreation(new Date());
        return specialiteRepository.save(specialite);
    }


    public Specialite update(String id, Specialite specialiteDetails) {
        Specialite specialite = findById(id);

        // Verify if another specialite with the same name exists
        if (!specialite.getNom().equals(specialiteDetails.getNom()) &&
                specialiteRepository.existsByNom(specialiteDetails.getNom())) {
            throw new IllegalArgumentException("Une spécialité avec ce nom existe déjà");
        }

        specialite.setNom(specialiteDetails.getNom());
        specialite.setDescription(specialiteDetails.getDescription());
        specialite.setDateModification(new Date());

        return specialiteRepository.save(specialite);
    }

    public void delete(String id) {
        Specialite specialite = findById(id);
        specialiteRepository.delete(specialite);
    }
}
