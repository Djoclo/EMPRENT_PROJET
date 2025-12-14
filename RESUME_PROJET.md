# 📚 Résumé du Projet - Système de Bibliothèque

## ✅ Conformité aux Exigences du Professeur

### ✅ Usagers de Test
- **Jean** : Numéro de compte `jean123` / NIP `1234`
- **Jeanne** : Numéro de compte `jeanne456` / NIP `5678`

### ✅ Catalogue de Livres
- **10 livres** dans le catalogue (conforme à l'exigence d'une dizaine)
- Format : id, titre, auteur, edition, annee, pages
- Fichier : `data/livres.csv`

### ✅ Format des Données
- **RFID** : 6 chiffres (ex: `123456`, `234567`, etc.)
- **NIP** : 4 chiffres (ex: `1234`, `5678`)
- **Numéro de compte** : Format texte (ex: `jean123`, `jeanne456`)

### ✅ Cas d'Utilisation Implémentés
1. **CU-001 : Démarrer le système**
   - Charge les usagers depuis `usagers.csv`
   - Charge les livres depuis `livres.csv`
   - Charge les exemplaires depuis `exemplaires.csv`
   - Initialise le catalogue

2. **CU-002 : Emprunter documents**
   - Authentification usager (numéro de compte + NIP)
   - Saisie RFID (simulation du lecteur RFID)
   - Vérification disponibilité
   - Création de l'emprunt (21 jours)
   - Options de reçu (imprimé, courriel, pas de reçu)

## 🎨 Interface Utilisateur

### Écran 1 : Connexion
- Zone d'image pour la carte de bibliothèque
- Instructions : "Placez votre carte de bibliothèque sous la ligne de balayage"
- Champs de connexion manuelle (numéro de compte + NIP)
- **Clavier virtuel numérique** pour la saisie

### Écran 2 : Emprunt
- Saisie du RFID de l'exemplaire
- Liste des exemplaires à emprunter
- Boutons : "Ajouter exemplaire" et "Finaliser les emprunts"
- Zone d'affichage des informations

### Écran 3 : Récapitulatif et Reçu
- Affichage du nombre de documents empruntés
- Liste avec titres, auteurs et dates de retour
- **Menu de choix de reçu** avec icônes :
  - 🖨️ Reçu imprimé
  - 📧 Reçu par courriel
  - ❌ Pas de reçu

## 📁 Structure du Projet

```
MODELISATION2/
├── src/main/java/com/bibliotheque/    # Code source
│   ├── model/          # Classes métier
│   ├── dao/            # Accès aux données
│   ├── controller/     # Contrôleurs
│   ├── system/         # Système principal
│   ├── ui/             # Interface utilisateur
│   └── Main.java       # Point d'entrée
├── data/               # Fichiers CSV
│   ├── usagers.csv     # 2 usagers (jean123, jeanne456)
│   ├── livres.csv      # 10 livres
│   ├── exemplaires.csv # Exemplaires avec RFID
│   └── emprunts.log    # Log des emprunts
├── docs/               # Diagrammes UML
│   └── diagrams/
├── build/              # Classes compilées
├── bibliotheque.jar    # Application exécutable
├── Documentation_Projet.md
├── Livrable_final.md
├── README.md
└── INSTRUCTIONS_PARTAGE.md
```

## 🚀 Exécution

### Méthode 1 : JAR (Recommandé)
```bash
java -jar bibliotheque.jar
```

### Méthode 2 : IDE
1. Importer le projet
2. Exécuter `com.bibliotheque.Main`

## 📋 Test Rapide

1. **Lancer l'application** : `java -jar bibliotheque.jar`
2. **Se connecter** :
   - Numéro de compte : `jean123`
   - NIP : `1234`
3. **Emprunter un livre** :
   - RFID : `123456` (ou tout autre RFID valide)
   - Cliquer sur "Ajouter exemplaire"
   - Cliquer sur "Finaliser les emprunts"
4. **Choisir un reçu** : Sélectionner une option (imprimé, courriel, ou pas de reçu)

## ✨ Fonctionnalités

✅ Authentification usager  
✅ Emprunt de documents via RFID (6 chiffres)  
✅ Gestion multi-exemplaires  
✅ Calcul automatique des dates (21 jours)  
✅ Interface graphique moderne avec clavier virtuel  
✅ Options de reçu (imprimé, courriel, pas de reçu)  
✅ Affichage récapitulatif des emprunts  
✅ Sauvegarde des emprunts dans `data/emprunts.log`  

## 📝 Notes Techniques

- **Architecture** : MVC simplifié
- **Interface** : Java Swing
- **Données** : Fichiers CSV (compatible Excel)
- **Durée de prêt** : 21 jours
- **Java** : JDK 8+

---

**Projet prêt pour le partage et la soumission ! ✅**

