# Système de Bibliothèque - Borne de Prêt Libre-Service

**Projet INF1163 - Modélisation et conception orientée objet**  
**Université du Québec en Outaouais (UQO)**  
**Équipe 9 (4 membres)**  
**Développé avec Eclipse**

---

## 📋 Description

Système de contrôle pour une borne de prêt en libre-service dans une bibliothèque. Permet aux usagers de s'authentifier et d'emprunter des documents de manière autonome.

## 🚀 Démarrage rapide

### Prérequis

- Java JDK 8 ou supérieur
- Fichiers CSV dans le dossier `data/`

### Compilation et exécution

**Méthode 1 : Depuis un IDE (recommandé)**
1. Ouvrir le projet dans Eclipse, IntelliJ IDEA ou NetBeans
2. Compiler le projet
3. Exécuter la classe `com.bibliotheque.Main`

**Méthode 2 : En ligne de commande**
```bash
# Compilation
javac -d build -sourcepath src/main/java src/main/java/com/bibliotheque/**/*.java

# Exécution
java -cp build com.bibliotheque.Main
```

## 📁 Structure du projet

```
INF1163_Projet_Equipe_9/
├── src/main/java/com/bibliotheque/
│   ├── model/          # Classes métier
│   ├── dao/            # Accès aux données
│   ├── controller/     # Contrôleurs
│   ├── system/         # Système principal
│   ├── ui/             # Interface utilisateur
│   └── Main.java       # Point d'entrée
├── data/               # Fichiers CSV
│   ├── usagers.csv
│   ├── livres.csv
│   └── exemplaires.csv
├── Documentation_Projet.md
└── README.md
```

## 👤 Utilisation

### Connexion
- **Numéro de compte:** Ex. `jean123` ou `jeanne456`
- **NIP:** 4 chiffres

### Emprunt
- **RFID:** 6 chiffres, ex. `123456`

### Données de test
- **Usager 1:** Numéro de compte: `jean123` / NIP: `1234` (Jean)
- **Usager 2:** Numéro de compte: `jeanne456` / NIP: `5678` (Jeanne)
- **RFID disponibles:** `123456`, `234567`, `345678`, `456789`, `567890`, etc.

## 📚 Documentation

Consulter `Documentation_Projet.md` pour la documentation complète :
- Cas d'utilisation détaillés
- Diagrammes UML
- Règles de gestion
- Architecture du système

## ⚙️ Fonctionnalités

✅ Authentification usager (numéro de compte + NIP)  
✅ Emprunt de documents via RFID  
✅ Consultation des emprunts  
✅ Gestion automatique des dates (21 jours)  
✅ Interface graphique intuitive  

## 📝 Notes

- Durée de prêt : **21 jours**
- Format RFID : **6 chiffres**
- Format NIP : **4 chiffres**
- Données stockées dans des fichiers **CSV** (compatible Excel)

## 👥 Équipe 9

Développé dans le cadre du cours INF1163 - UQO

---

**Version:** 1.0

