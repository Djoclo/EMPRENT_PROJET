# 📁 Fichiers Importants vs Fichiers à Supprimer

## ✅ FICHIERS IMPORTANTS (À GARDER)

### 📂 Code Source (ESSENTIEL)
```
src/
└── main/java/com/bibliotheque/
    ├── Main.java                    ✅ Point d'entrée
    ├── controller/
    │   └── ControleurEmprunt.java  ✅ Contrôleur
    ├── dao/
    │   └── GestionnaireFichiers.java ✅ Accès aux données
    ├── model/                       ✅ Classes métier
    │   ├── Catalogue.java
    │   ├── Emprunt.java
    │   ├── Exemplaire.java
    │   ├── Livre.java
    │   └── Usager.java
    ├── system/
    │   └── SystemeBibliotheque.java ✅ Système principal
    └── ui/
        └── InterfaceUtilisateur.java ✅ Interface graphique
```

### 📂 Données (ESSENTIEL)
```
data/
├── usagers.csv      ✅ Données des usagers (jean123, jeanne456)
├── livres.csv       ✅ Catalogue des 10 livres
└── exemplaires.csv ✅ Exemplaires avec RFID
```

### 📦 Application Exécutable (ESSENTIEL)
```
bibliotheque.jar    ✅ Application compilée (à partager)
MANIFEST.MF        ✅ Manifeste pour le JAR
```

### 📚 Documentation (IMPORTANT)
```
Documentation_Projet.md  ✅ Documentation complète du projet
Livrable_final.md        ✅ Livrable formaté pour le prof
README.md                ✅ Instructions générales
RESUME_PROJET.md         ✅ Résumé du projet
INSTRUCTIONS_PARTAGE.md  ✅ Instructions pour coéquipiers
```

### 📊 Diagrammes UML (IMPORTANT)
```
docs/
└── diagrams/
    ├── couche_affaires_classes.puml  ✅ Diagramme de classes
    └── emprunter_sequence.puml       ✅ Diagramme de séquence
```

### 🛠️ Outils (UTILE)
```
plantuml.jar       ✅ Pour générer les diagrammes UML
creerJar.ps1       ✅ Script pour créer le JAR (optionnel)
.gitignore         ✅ Fichiers à ignorer (utile pour Git)
```

### 📄 Référence (UTILE)
```
Projet-Énoncé.pdf  ✅ Énoncé du projet (référence)
```

---

## ❌ FICHIERS À SUPPRIMER (NON NÉCESSAIRES)

### 🗑️ Fichiers Compilés (Générés automatiquement)
```
bin/               ❌ Classes compilées (peut être régénéré)
build/             ❌ Classes compilées (peut être régénéré)
```

### 🗑️ Fichiers de Configuration IDE (Spécifiques à votre IDE)
```
.project           ❌ Configuration Eclipse
.classpath         ❌ Configuration Eclipse
.settings/          ❌ Paramètres Eclipse (si existe)
.idea/             ❌ Configuration IntelliJ (si existe)
*.iml              ❌ Modules IntelliJ (si existe)
```

### 🗑️ Fichiers Temporaires
```
dist/              ❌ Dossier temporaire (vide ou fichiers de test)
data/emprunts.log  ❌ Log des emprunts (peut être régénéré)
```

### 🗑️ Fichiers Système (Si présents)
```
.DS_Store          ❌ macOS
Thumbs.db          ❌ Windows
*.swp              ❌ Vim
*.swo              ❌ Vim
*~                 ❌ Sauvegardes
```

---

## 📋 RÉSUMÉ POUR LE PARTAGE

### ✅ À INCLURE dans le dossier partagé :
1. **src/** - Tout le code source Java
2. **data/** - Tous les fichiers CSV (usagers.csv, livres.csv, exemplaires.csv)
3. **bibliotheque.jar** - Application exécutable
4. **MANIFEST.MF** - Manifeste du JAR
5. **Documentation_Projet.md** - Documentation complète
6. **Livrable_final.md** - Livrable formaté
7. **README.md** - Instructions
8. **docs/** - Diagrammes UML
9. **plantuml.jar** - Pour générer les diagrammes (optionnel)
10. **.gitignore** - Si utilisation de Git
11. **Projet-Énoncé.pdf** - Référence (optionnel)

### ❌ À EXCLURE du dossier partagé :
1. **bin/** - Fichiers compilés (régénérables)
2. **build/** - Fichiers compilés (régénérables)
3. **.project** - Configuration IDE
4. **.classpath** - Configuration IDE
5. **dist/** - Fichiers temporaires
6. **data/emprunts.log** - Log (régénérable)

---

## 🎯 STRUCTURE MINIMALE POUR LE PARTAGE

```
MODELISATION2/
├── src/                    ✅ Code source
├── data/                   ✅ Données CSV
│   ├── usagers.csv
│   ├── livres.csv
│   └── exemplaires.csv
├── docs/                   ✅ Diagrammes
│   └── diagrams/
├── bibliotheque.jar       ✅ Application
├── MANIFEST.MF            ✅ Manifeste
├── Documentation_Projet.md ✅ Documentation
├── Livrable_final.md      ✅ Livrable
├── README.md              ✅ Instructions
└── .gitignore            ✅ Git (optionnel)
```

**Taille estimée : ~50-100 KB** (sans les fichiers compilés)

---

## 💡 NOTE IMPORTANTE

Les dossiers **bin/** et **build/** peuvent être supprimés car :
- Ils contiennent des fichiers `.class` générés automatiquement
- Ils peuvent être recréés en compilant le code source
- Le JAR (`bibliotheque.jar`) contient déjà tout ce qui est nécessaire pour exécuter l'application

**Pour recompiler après suppression :**
```bash
javac -d build -sourcepath src/main/java src/main/java/com/bibliotheque/**/*.java
```

