package com.dhag.praticien.api.praticien.api.repository;

import java.util.List;
import java.util.Optional;

import com.dhag.praticien.api.praticien.api.model.Praticien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PraticienRepository  extends MongoRepository<Praticien, String> {

    Optional<Praticien> findByEmail(String email);

    List<Praticien> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    @Query("{'specialites.$id': ?0}")
    List<Praticien> findBySpecialiteId(String specialiteId);

    List<Praticien> findByActif(boolean actif);

    boolean existsByEmail(String email);

}
