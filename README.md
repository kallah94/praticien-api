# API Praticien (Backend)

Une API complète pour la gestion des praticiens médicaux et de leurs spécialités. Ce backend fournit toutes les fonctionnalités nécessaires pour gérer les données des praticiens dans un contexte médical.

## Table des matières

- [Fonctionnalités](#fonctionnalités)
- [Stack Technologique](#stack-technologique)
- [Structure du Projet](#structure-du-projet)
- [Démarrage](#démarrage)
    - [Prérequis](#prérequis)
    - [Installation](#installation)
    - [Exécution de l'application](#exécution-de-lapplication)
- [Documentation de l'API](#documentation-de-lapi)
- [Sécurité](#sécurité)
- [Modèle de données](#modèle-de-données)
- [Données d'exemple](#données-dexemple)
- [Licence](#licence)

## Fonctionnalités

- **Gestion des praticiens** : Création, lecture, mise à jour et suppression des praticiens médicaux
- **Gestion des spécialités** : Gestion des spécialités médicales
- **Support d'adresses multiples** : Suivi de différents types d'adresses pour chaque praticien (Bureau, Officielle, Domicile)
- **Attribution de spécialité** : Attribution de plusieurs spécialités aux praticiens
- **Filtrage avancé** : Recherche et filtrage des praticiens par nom ou spécialité
- **Documentation Swagger** : Documentation interactive de l'API
- **Spring Security** : Authentification basique pour protéger l'API
- **Initialisation des données** : Données d'exemple dans le contexte sénégalais

## Stack technologique

- Java 21
- Spring Boot 3.2
- Spring Data MongoDB
- Spring Security
- SpringDoc OpenAPI (Swagger)
- Maven
- MongoDB

## Structure du projet

```
praticien-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── praticienapi/
│   │   │               ├── PraticienApiApplication.java
│   │   │               ├── config/
│   │   │               │   ├── DataInitializer.java
│   │   │               │   ├── MongoDBConfig.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── PraticienController.java
│   │   │               │   └── SpecialiteController.java
│   │   │               ├── exception/
│   │   │               │   ├── ResourceNotFoundException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── model/
│   │   │               │   ├── Adresse.java
│   │   │               │   ├── Praticien.java
│   │   │               │   └── Specialite.java
│   │   │               ├── repository/
│   │   │               │   ├── PraticienRepository.java
│   │   │               │   └── SpecialiteRepository.java
│   │   │               └── service/
│   │   │                   ├── PraticienService.java
│   │   │                   └── SpecialiteService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Démarrage

### Prérequis

- Java 21 ou supérieur
- Maven
- MongoDB

### Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votrenomdutilisateur/praticien-api.git
   cd praticien-api
   ```

2. Installez les dépendances backend :
   ```bash
   mvn install
   ```

3. Configurez la connexion MongoDB dans `application.properties` :
   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=praticien_db
   ```

### Exécution de l'application

1. Démarrez MongoDB :
   ```bash
   mongod
   ```

2. Exécutez le backend :
   ```bash
   mvn spring-boot:run
   ```

3. Accédez à l'application :
    - Swagger UI : http://localhost:8080/swagger-ui/index.html#/
    - URL de base de l'API : http://localhost:8080/api

## Documentation de l'API

La documentation de l'API est disponible via Swagger UI à l'adresse http://localhost:8080/swagger-ui.html lorsque l'application est en cours d'exécution.

### Points d'accès principaux

- **Praticiens** :
    - `GET /api/praticiens` : Obtenir tous les praticiens
    - `GET /api/praticiens/{id}` : Obtenir un praticien par ID
    - `GET /api/praticiens/search?search={term}` : Rechercher des praticiens par nom
    - `GET /api/praticiens/specialite/{specialiteId}` : Obtenir des praticiens par spécialité
    - `POST /api/praticiens` : Créer un nouveau praticien
    - `PUT /api/praticiens/{id}` : Mettre à jour un praticien
    - `DELETE /api/praticiens/{id}` : Supprimer un praticien

- **Spécialités** :
    - `GET /api/specialites` : Obtenir toutes les spécialités
    - `GET /api/specialites/{id}` : Obtenir une spécialité par ID
    - `GET /api/specialites/search?nom={term}` : Rechercher des spécialités par nom
    - `POST /api/specialites` : Créer une nouvelle spécialité
    - `PUT /api/specialites/{id}` : Mettre à jour une spécialité
    - `DELETE /api/specialites/{id}` : Supprimer une spécialité

- **Adresses** :
    - `GET /api/praticiens/{id}/adresses` : Obtenir toutes les adresses d'un praticien
    - `GET /api/praticiens/{id}/adresses/type/{type}` : Obtenir les adresses par type
    - `POST /api/praticiens/{id}/adresses` : Ajouter une adresse à un praticien
    - `PUT /api/praticiens/{id}/adresses/{index}` : Mettre à jour l'adresse d'un praticien
    - `DELETE /api/praticiens/{id}/adresses/{index}` : Supprimer l'adresse d'un praticien

## Sécurité

L'application utilise Spring Security avec l'authentification basique :

- Identifiants par défaut :
    - Nom d'utilisateur : Admin
    - Mot de passe : Mouride@2024

Pour modifier ces identifiants, modifiez le fichier `application.properties` :

```properties
spring.security.user.name=Admin
spring.security.user.password=Mouride@2024
```

## Modèle de données

### Praticien
- **id** : Identifiant unique
- **nom** : Nom de famille
- **prenom** : Prénom
- **email** : Adresse e-mail
- **telephone** : Numéro de téléphone
- **adresses** : Liste d'adresses
- **specialites** : Liste de spécialités
- **actif** : Statut actif
- **dateCreation** : Date de création
- **dateModification** : Date de dernière modification

### Spécialité
- **id** : Identifiant unique
- **nom** : Nom de la spécialité
- **description** : Description
- **dateCreation** : Date de création
- **dateModification** : Date de dernière modification

### Adresse
- **type** : Type d'adresse (OFFICE, OFFICIEL, HOME)
- **rue** : Rue
- **complementRue** : Informations d'adresse supplémentaires
- **codePostal** : Code postal
- **ville** : Ville
- **pays** : Pays
- **adressePrincipale** : Indicateur d'adresse principale

## Données d'exemple

L'application est livrée avec un `DataInitializer` qui remplit la base de données avec des données d'exemple au premier démarrage :

- 10 spécialités médicales
- 7 praticiens avec des noms et adresses sénégalais
- Plusieurs types d'adresses
- Attributions réalistes de spécialités

## Licence
None

## Auteur
Moussa FALL (kallah94)

---

© 2025 Système de gestion de l'API Praticien