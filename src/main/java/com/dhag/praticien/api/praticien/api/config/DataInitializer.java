package com.dhag.praticien.api.praticien.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dhag.praticien.api.praticien.api.model.Adresse;
import com.dhag.praticien.api.praticien.api.model.Praticien;
import com.dhag.praticien.api.praticien.api.model.Specialite;
import com.dhag.praticien.api.praticien.api.repository.PraticienRepository;
import com.dhag.praticien.api.praticien.api.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PraticienRepository praticienRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Override
    public void run(String... args) {
        // Check if data already exists
        if (praticienRepository.count() > 0 || specialiteRepository.count() > 0) {
            System.out.println("Database already initialized, skipping data initialization");
            return;
        }

        System.out.println("Starting data initialization...");

        // Create specialties
        List<Specialite> specialites = createSpecialites();

        // Create practitioners
        createPraticiens(specialites);

        System.out.println("Data initialization completed successfully!");
    }

    private List<Specialite> createSpecialites() {
        List<Specialite> specialites = new ArrayList<>();

        // Create Cardiology specialty
        Specialite cardiology = new Specialite();
        cardiology.setNom("Cardiologie");
        cardiology.setDescription("Spécialité médicale qui traite des affections du cœur et des vaisseaux sanguins.");
        cardiology.setDateCreation(new Date());
        specialites.add(cardiology);

        // Create Pediatrics specialty
        Specialite pediatrics = new Specialite();
        pediatrics.setNom("Pédiatrie");
        pediatrics.setDescription("Branche de la médecine qui se consacre aux soins médicaux des enfants et des adolescents.");
        pediatrics.setDateCreation(new Date());
        specialites.add(pediatrics);

        // Create Gynecology specialty
        Specialite gynecology = new Specialite();
        gynecology.setNom("Gynécologie");
        gynecology.setDescription("Spécialité médicale qui s'occupe de la santé du système reproducteur féminin.");
        gynecology.setDateCreation(new Date());
        specialites.add(gynecology);

        // Create Dermatology specialty
        Specialite dermatology = new Specialite();
        dermatology.setNom("Dermatologie");
        dermatology.setDescription("Spécialité médicale consacrée au diagnostic et au traitement des maladies de la peau.");
        dermatology.setDateCreation(new Date());
        specialites.add(dermatology);

        // Create Ophthalmology specialty
        Specialite ophthalmology = new Specialite();
        ophthalmology.setNom("Ophtalmologie");
        ophthalmology.setDescription("Spécialité médicale qui concerne le diagnostic et le traitement des maladies des yeux.");
        ophthalmology.setDateCreation(new Date());
        specialites.add(ophthalmology);

        // Create Tropical Medicine specialty
        Specialite tropicalMedicine = new Specialite();
        tropicalMedicine.setNom("Médecine Tropicale");
        tropicalMedicine.setDescription("Spécialité qui traite des maladies prévalentes dans les régions tropicales et subtropicales.");
        tropicalMedicine.setDateCreation(new Date());
        specialites.add(tropicalMedicine);

        // Create Neurology specialty
        Specialite neurology = new Specialite();
        neurology.setNom("Neurologie");
        neurology.setDescription("Spécialité médicale qui étudie et traite les troubles du système nerveux.");
        neurology.setDateCreation(new Date());
        specialites.add(neurology);

        // Create General Medicine specialty
        Specialite generalMedicine = new Specialite();
        generalMedicine.setNom("Médecine Générale");
        generalMedicine.setDescription("Pratique médicale qui prend en charge les soins primaires et le suivi global des patients.");
        generalMedicine.setDateCreation(new Date());
        specialites.add(generalMedicine);

        // Create Pneumology specialty
        Specialite pneumology = new Specialite();
        pneumology.setNom("Pneumologie");
        pneumology.setDescription("Spécialité médicale qui s'occupe du diagnostic et du traitement des maladies respiratoires.");
        pneumology.setDateCreation(new Date());
        specialites.add(pneumology);

        // Create Radiology specialty
        Specialite radiology = new Specialite();
        radiology.setNom("Radiologie");
        radiology.setDescription("Spécialité médicale utilisant l'imagerie pour diagnostiquer et traiter les maladies.");
        radiology.setDateCreation(new Date());
        specialites.add(radiology);

        // Save all specialties to the database
        return specialiteRepository.saveAll(specialites);
    }

    private void createPraticiens(List<Specialite> specialites) {
        List<Praticien> praticiens = new ArrayList<>();

        Praticien diop = new Praticien();
        diop.setNom("Diop");
        diop.setPrenom("Moustapha");
        diop.setEmail("moustapha.diop@medecine.sn");
        diop.setTelephone("+221770001122");
        diop.setActif(true);
        diop.setDateCreation(new Date());
        diop.setSpecialites(Arrays.asList(specialites.get(0), specialites.get(7))); // Cardiology and General Medicine

        List<Adresse> diopAdresses = new ArrayList<>();

        Adresse diopWork = new Adresse();
        diopWork.setType(Adresse.AdresseType.OFFICE);
        diopWork.setRue("Avenue Cheikh Anta Diop");
        diopWork.setComplementRue("Centre Hospitalier Universitaire");
        diopWork.setCodePostal("10700");
        diopWork.setVille("Dakar");
        diopWork.setPays("Sénégal");
        diopWork.setAdressePrincipale(true);
        diopAdresses.add(diopWork);

        Adresse diopHome = new Adresse();
        diopHome.setType(Adresse.AdresseType.HOME);
        diopHome.setRue("Rue 10 Fann Residence");
        diopHome.setCodePostal("12500");
        diopHome.setVille("Dakar");
        diopHome.setPays("Sénégal");
        diopHome.setAdressePrincipale(true);
        diopAdresses.add(diopHome);

        diop.setAdresses(diopAdresses);
        praticiens.add(diop);

        Praticien sall = new Praticien();
        sall.setNom("Sall");
        sall.setPrenom("Fatou");
        sall.setEmail("fatou.sall@medecine.sn");
        sall.setTelephone("+221780112233");
        sall.setActif(true);
        sall.setDateCreation(new Date());
        sall.setSpecialites(Arrays.asList(specialites.get(2), specialites.get(1))); // Gynecology and Pediatrics

        List<Adresse> sallAdresses = new ArrayList<>();

        Adresse sallWork = new Adresse();
        sallWork.setType(Adresse.AdresseType.OFFICE);
        sallWork.setRue("Rue Aimé Césaire");
        sallWork.setComplementRue("Clinique du Plateau");
        sallWork.setCodePostal("11500");
        sallWork.setVille("Dakar");
        sallWork.setPays("Sénégal");
        sallWork.setAdressePrincipale(true);
        sallAdresses.add(sallWork);

        sall.setAdresses(sallAdresses);
        praticiens.add(sall);

        Praticien ndiaye = new Praticien();
        ndiaye.setNom("Ndiaye");
        ndiaye.setPrenom("Amadou");
        ndiaye.setEmail("amadou.ndiaye@medecine.sn");
        ndiaye.setTelephone("+221760223344");
        ndiaye.setActif(true);
        ndiaye.setDateCreation(new Date());
        ndiaye.setSpecialites(Arrays.asList(specialites.get(5), specialites.get(8))); // Tropical Medicine and Pneumology

        List<Adresse> ndiayeAdresses = new ArrayList<>();

        Adresse ndiayeWork = new Adresse();
        ndiayeWork.setType(Adresse.AdresseType.OFFICE);
        ndiayeWork.setRue("Avenue de la Liberté");
        ndiayeWork.setComplementRue("Hôpital Principal");
        ndiayeWork.setCodePostal("10600");
        ndiayeWork.setVille("Dakar");
        ndiayeWork.setPays("Sénégal");
        ndiayeWork.setAdressePrincipale(true);
        ndiayeAdresses.add(ndiayeWork);

        Adresse ndiayeOfficial = new Adresse();
        ndiayeOfficial.setType(Adresse.AdresseType.OFFICIEL);
        ndiayeOfficial.setRue("Avenue Léopold Sédar Senghor");
        ndiayeOfficial.setComplementRue("Ministère de la Santé");
        ndiayeOfficial.setCodePostal("10500");
        ndiayeOfficial.setVille("Dakar");
        ndiayeOfficial.setPays("Sénégal");
        ndiayeOfficial.setAdressePrincipale(true);
        ndiayeAdresses.add(ndiayeOfficial);

        ndiaye.setAdresses(ndiayeAdresses);
        praticiens.add(ndiaye);

        Praticien fall = new Praticien();
        fall.setNom("Fall");
        fall.setPrenom("Aissatou");
        fall.setEmail("aissatou.fall@medecine.sn");
        fall.setTelephone("+221770334455");
        fall.setActif(true);
        fall.setDateCreation(new Date());
        fall.setSpecialites(Arrays.asList(specialites.get(4), specialites.get(9))); // Ophthalmology and Radiology

        List<Adresse> fallAdresses = new ArrayList<>();

        Adresse fallWork = new Adresse();
        fallWork.setType(Adresse.AdresseType.OFFICE);
        fallWork.setRue("Route de Ouakam");
        fallWork.setComplementRue("Hôpital Militaire de Ouakam");
        fallWork.setCodePostal("12100");
        fallWork.setVille("Dakar");
        fallWork.setPays("Sénégal");
        fallWork.setAdressePrincipale(true);
        fallAdresses.add(fallWork);

        fall.setAdresses(fallAdresses);
        praticiens.add(fall);

        Praticien mbaye = new Praticien();
        mbaye.setNom("Mbaye");
        mbaye.setPrenom("Abdoulaye");
        mbaye.setEmail("abdoulaye.mbaye@medecine.sn");
        mbaye.setTelephone("+221780445566");
        mbaye.setActif(false); // Inactive practitioner
        mbaye.setDateCreation(new Date());
        mbaye.setSpecialites(Arrays.asList(specialites.get(3), specialites.get(6))); // Dermatology and Neurology

        List<Adresse> mbayeAdresses = new ArrayList<>();

        Adresse mbayeWork = new Adresse();
        mbayeWork.setType(Adresse.AdresseType.OFFICE);
        mbayeWork.setRue("Corniche Ouest");
        mbayeWork.setComplementRue("Clinique des Mamelles");
        mbayeWork.setCodePostal("12000");
        mbayeWork.setVille("Dakar");
        mbayeWork.setPays("Sénégal");
        mbayeWork.setAdressePrincipale(true);
        mbayeAdresses.add(mbayeWork);

        mbaye.setAdresses(mbayeAdresses);
        praticiens.add(mbaye);


        Praticien seck = new Praticien();
        seck.setNom("Seck");
        seck.setPrenom("Omar");
        seck.setEmail("omar.seck@medecine.sn");
        seck.setTelephone("+221760556677");
        seck.setActif(true);
        seck.setDateCreation(new Date());
        seck.setSpecialites(Arrays.asList(specialites.get(7))); // General Medicine

        List<Adresse> seckAdresses = new ArrayList<>();

        Adresse seckWork = new Adresse();
        seckWork.setType(Adresse.AdresseType.OFFICE);
        seckWork.setRue("Avenue Général de Gaulle");
        seckWork.setComplementRue("Hôpital Régional");
        seckWork.setCodePostal("32000");
        seckWork.setVille("Saint-Louis");
        seckWork.setPays("Sénégal");
        seckWork.setAdressePrincipale(true);
        seckAdresses.add(seckWork);

        seck.setAdresses(seckAdresses);
        praticiens.add(seck);

        Praticien gueye = new Praticien();
        gueye.setNom("Gueye");
        gueye.setPrenom("Mariama");
        gueye.setEmail("mariama.gueye@medecine.sn");
        gueye.setTelephone("+221770667788");
        gueye.setActif(true);
        gueye.setDateCreation(new Date());
        gueye.setSpecialites(Arrays.asList(specialites.get(1), specialites.get(7))); // Pediatrics and General Medicine

        List<Adresse> gueyeAdresses = new ArrayList<>();

        Adresse gueyeWork = new Adresse();
        gueyeWork.setType(Adresse.AdresseType.OFFICE);
        gueyeWork.setRue("Avenue Léopold Sédar Senghor");
        gueyeWork.setComplementRue("Centre de Santé");
        gueyeWork.setCodePostal("21000");
        gueyeWork.setVille("Thiès");
        gueyeWork.setPays("Sénégal");
        gueyeWork.setAdressePrincipale(true);
        gueyeAdresses.add(gueyeWork);
        System.out.println("Test Test");
        gueye.setAdresses(gueyeAdresses);
        praticiens.add(gueye);

        praticienRepository.saveAll(praticiens);
    }
}