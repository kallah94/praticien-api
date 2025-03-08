package com.dhag.praticien.api.praticien.api.repository;

import java.util.List;
import java.util.Optional;

import com.dhag.praticien.api.praticien.api.model.Specialite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialiteRepository extends MongoRepository<Specialite, String> {

    Optional<Specialite> findByNom(String nom);

    List<Specialite> findByNomContaining(String nom);

    boolean existsByNom(String nom);
}
