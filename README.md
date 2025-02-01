# API REST - Gestion de Bibliothèque

## Description

Cette API REST permet de gérer une bibliothèque en offrant des fonctionnalités d'authentification, de gestion des livres, des emprunts et des auteurs. L'authentification est gérée via Basic Auth et JWT.

## Technologies utilisées

- Java 17+
- Spring Boot
- Spring Security (Basic Auth & JWT)
- Spring Data JPA
- PostgreSQL / MySQL
- Lombok
- Maven

## Endpoints

### Authentification

- **POST /api/auth/signup** : Inscription d'un nouvel utilisateur
- **POST /api/auth/login** : Connexion et récupération d'un token JWT
- **POST /api/auth/change-password** : Modification du mot de passe

### Gestion des livres

- **GET /api/livres** : Récupérer la liste des livres
- **POST /api/livres/{id}/rate** : Évaluer un livre (nécessite une authentification)

### Gestion des emprunts

- **GET /api/emprunts** : Récupérer la liste des emprunts d'un utilisateur (nécessite une authentification)

### Gestion des auteurs

- **GET /api/auteurs** : Récupérer la liste des auteurs

## Sécurité & Authentification

L'API utilise deux méthodes d'authentification :

- **Basic Auth** : Pour l'accès initial et la gestion des comptes.
- **JWT (JSON Web Token)** : Pour sécuriser les requêtes après l'authentification.

### Exemple de requête authentifiée (JWT)

```bash
curl -H "Authorization: Bearer <votre_token>" http://localhost:8080/api/livres
