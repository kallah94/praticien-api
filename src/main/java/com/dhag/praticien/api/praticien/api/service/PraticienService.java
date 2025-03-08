package com.dhag.praticien.api.praticien.api.service;

import java.util.Date;
import java.util.List;

import com.dhag.praticien.api.praticien.api.exception.ResourceNotFoundException;
import com.dhag.praticien.api.praticien.api.model.Adresse;
import com.dhag.praticien.api.praticien.api.model.Praticien;
import com.dhag.praticien.api.praticien.api.model.Specialite;
import com.dhag.praticien.api.praticien.api.repository.PraticienRepository;
import com.dhag.praticien.api.praticien.api.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraticienService {

    @Autowired
    private PraticienRepository praticienRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    public List<Praticien> findAll() {
        return praticienRepository.findAll();
    }

    public Praticien findById(String id) {
        return praticienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Praticien", "id", id));
    }

    public List<Praticien> findByNomOrPrenom(String search) {
        return praticienRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(search, search);
    }

    public List<Praticien> findBySpecialite(String specialiteId) {

        specialiteRepository.findById(specialiteId)
                            .orElseThrow(() -> new ResourceNotFoundException("Spécialité", "id", specialiteId));

        return praticienRepository.findBySpecialiteId(specialiteId);
    }

    public Praticien create(Praticien praticien) {
        if (praticienRepository.existsByEmail(praticien.getEmail())) {
            throw new IllegalArgumentException("Un praticien avec cet email existe déjà");
        }
        praticien.setDateCreation(new Date());
        return praticienRepository.save(praticien);
    }

    public Praticien update(String id, Praticien praticienDetails) {
        Praticien praticien = findById(id);

        if (!praticien.getEmail().equals(praticienDetails.getEmail()) &&
                praticienRepository.existsByEmail(praticienDetails.getEmail())) {
            throw new IllegalArgumentException("Un praticien avec cet email existe déjà");
        }

        praticien.setNom(praticienDetails.getNom());
        praticien.setPrenom(praticienDetails.getPrenom());
        praticien.setEmail(praticienDetails.getEmail());
        praticien.setTelephone(praticienDetails.getTelephone());
        praticien.setAdresses(praticienDetails.getAdresses());
        praticien.setActif(praticienDetails.isActif());
        praticien.setDateModification(new Date());
        praticien.setSpecialites(praticienDetails.getSpecialites());
        return praticienRepository.save(praticien);
    }

    public void delete(String id) {
        Praticien praticien = findById(id);
        praticienRepository.delete(praticien);
    }

    public Praticien addSpecialite(String praticienId, String specialiteId) {
        Praticien praticien = findById(praticienId);

        Specialite specialite = specialiteRepository.findById(specialiteId)
                                                    .orElseThrow(() -> new ResourceNotFoundException("Spécialité", "id", specialiteId));

        if (praticien.getSpecialites().stream().anyMatch(s -> s.getId().equals(specialiteId))) {
            throw new IllegalArgumentException("Cette spécialité est déjà assignée à ce praticien");
        }

        praticien.getSpecialites().add(specialite);
        praticien.setDateModification(new Date());

        return praticienRepository.save(praticien);
    }

    public Praticien removeSpecialite(String praticienId, String specialiteId) {
        Praticien praticien = findById(praticienId);

        // Verify if specialite exists
        specialiteRepository.findById(specialiteId)
                            .orElseThrow(() -> new ResourceNotFoundException("Spécialité", "id", specialiteId));

        // Remove specialite from praticien
        boolean removed = praticien.getSpecialites().removeIf(s -> s.getId().equals(specialiteId));

        if (!removed) {
            throw new IllegalArgumentException("Cette spécialité n'est pas assignée à ce praticien");
        }

        praticien.setDateModification(new Date());

        return praticienRepository.save(praticien);
    }

    public Praticien addAdresse(String praticienId, Adresse adresse) {
        Praticien praticien = findById(praticienId);

        // If this address is set as primary, remove primary flag from others of same type
        if (adresse.isAdressePrincipale()) {
            praticien.getAdresses().stream()
                     .filter(a -> a.getType() == adresse.getType() && a.isAdressePrincipale())
                     .forEach(a -> a.setAdressePrincipale(false));
        }

        praticien.getAdresses().add(adresse);
        praticien.setDateModification(new Date());

        return praticienRepository.save(praticien);
    }


    public Praticien updateAdresse(String praticienId, int adresseIndex, Adresse adresseDetails) {
        Praticien praticien = findById(praticienId);

        if (adresseIndex < 0 || adresseIndex >= praticien.getAdresses().size()) {
            throw new IllegalArgumentException("Index d'adresse invalide");
        }

        // If this address is set as primary, remove primary flag from others of same type
        if (adresseDetails.isAdressePrincipale()) {
            praticien.getAdresses().stream()
                     .filter(a -> a.getType() == adresseDetails.getType()
                             && a.isAdressePrincipale()
                             && praticien.getAdresses().indexOf(a) != adresseIndex)
                     .forEach(a -> a.setAdressePrincipale(false));
        }

        praticien.getAdresses().set(adresseIndex, adresseDetails);
        praticien.setDateModification(new Date());

        return praticienRepository.save(praticien);
    }


    public Praticien removeAdresse(String praticienId, int adresseIndex) {
        Praticien praticien = findById(praticienId);

        if (adresseIndex < 0 || adresseIndex >= praticien.getAdresses().size()) {
            throw new IllegalArgumentException("Index d'adresse invalide");
        }

        praticien.getAdresses().remove(adresseIndex);
        praticien.setDateModification(new Date());

        return praticienRepository.save(praticien);
    }

    public List<Adresse> getAdressesByType(String praticienId, Adresse.AdresseType type) {
        Praticien praticien = findById(praticienId);

        return praticien.getAdresses().stream()
                        .filter(adresse -> adresse.getType() == type)
                        .toList();
    }

}
