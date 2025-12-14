# Instructions pour le Partage du Projet

## 📦 Contenu du Dossier

Ce dossier contient le projet complet de système de bibliothèque pour le cours INF1163.

## ✅ Vérifications Effectuées

- ✅ **Usagers conformes aux exigences** : `jean123` et `jeanne456`
- ✅ **Catalogue avec 10 livres** : Tous les livres sont présents dans `data/livres.csv`
- ✅ **Format RFID** : 6 chiffres (conforme)
- ✅ **Format NIP** : 4 chiffres (conforme)
- ✅ **Interface utilisateur** : Complète avec clavier virtuel et écrans de connexion/emprunt/reçu

## 🚀 Démarrage Rapide

### Exécution avec JAR (Recommandé)
```bash
java -jar bibliotheque.jar
```

### Exécution depuis l'IDE
1. Importer le projet dans Eclipse/IntelliJ/NetBeans
2. Exécuter la classe `com.bibliotheque.Main`

## 👤 Comptes de Test

- **Jean** : Numéro de compte `jean123` / NIP `1234`
- **Jeanne** : Numéro de compte `jeanne456` / NIP `5678`

## 📋 RFID de Test

Exemples de RFID disponibles dans le système :
- `123456`, `234567`, `345678`, `456789`, `567890`, `678901`, `789012`, `890123`, `901234`, `012345`, `111111`, `222222`, `333333`, `444444`, `555555`

## 📁 Structure Importante

- `src/` : Code source Java
- `data/` : Fichiers CSV (usagers, livres, exemplaires)
- `docs/` : Diagrammes UML
- `bibliotheque.jar` : Application exécutable
- `Documentation_Projet.md` : Documentation complète
- `Livrable_final.md` : Livrable final formaté

## ⚠️ Notes Importantes

1. Les fichiers CSV doivent rester dans le dossier `data/`
2. Le JAR doit être exécuté depuis la racine du projet (pour accéder au dossier `data/`)
3. Les emprunts sont enregistrés dans `data/emprunts.log`

## 🔧 Compilation Manuelle

Si vous devez recompiler :
```bash
javac -d build -sourcepath src/main/java src/main/java/com/bibliotheque/**/*.java
```

Puis créer le JAR :
```bash
cd build
jar cvfm ../bibliotheque.jar ../MANIFEST.MF com
cd ..
```

## 📝 Conformité aux Exigences

✅ Cas d'utilisation "Emprunter documents" implémenté  
✅ Cas d'utilisation "Démarrer le système" implémenté  
✅ 2 usagers de test : jean123 et jeanne456  
✅ 10 livres dans le catalogue  
✅ RFID à 6 chiffres  
✅ NIP à 4 chiffres  
✅ Interface graphique complète  
✅ Diagrammes UML fournis  

---

**Projet prêt pour le partage et la soumission !**

