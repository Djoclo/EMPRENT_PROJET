# 📦 Guide Git - Créer un Dépôt et Pousser le Code

## 🚀 Étape 1 : Vérifier que Git est installé

Ouvrez PowerShell ou Terminal et vérifiez :
```bash
git --version
```

Si Git n'est pas installé, téléchargez-le sur : https://git-scm.com/download/win

---

## 📝 Étape 2 : Initialiser le dépôt Git (local)

Dans le dossier de votre projet :
```bash
cd "c:\Users\claud\Downloads\MODELISATION2"
git init
```

---

## 📋 Étape 3 : Configurer Git (si première fois)

```bash
git config --global user.name "Votre Nom"
git config --global user.email "votre.email@example.com"
```

---

## ✅ Étape 4 : Ajouter les fichiers au dépôt

### 4.1 Vérifier les fichiers à ajouter
```bash
git status
```

### 4.2 Ajouter tous les fichiers importants
```bash
git add src/
git add data/
git add docs/
git add bibliotheque.jar
git add MANIFEST.MF
git add *.md
git add .gitignore
git add plantuml.jar
```

**OU ajouter tout sauf les fichiers ignorés :**
```bash
git add .
```

### 4.3 Vérifier ce qui sera commité
```bash
git status
```

---

## 💾 Étape 5 : Créer le premier commit

```bash
git commit -m "Initial commit - Projet système de bibliothèque INF1163"
```

---

## 🌐 Étape 6 : Créer un dépôt sur GitHub (ou autre plateforme)

### Option A : GitHub
1. Allez sur https://github.com
2. Connectez-vous ou créez un compte
3. Cliquez sur le bouton **"+"** en haut à droite
4. Sélectionnez **"New repository"**
5. Donnez un nom (ex: `projet-bibliotheque-inf1163`)
6. **Ne cochez PAS** "Initialize with README" (vous avez déjà des fichiers)
7. Cliquez sur **"Create repository"**

### Option B : GitLab
1. Allez sur https://gitlab.com
2. Créez un nouveau projet
3. Suivez les instructions similaires

---

## 🔗 Étape 7 : Lier le dépôt local au dépôt distant

GitHub vous donnera une URL comme :
```
https://github.com/VOTRE_USERNAME/projet-bibliotheque-inf1163.git
```

**OU en SSH :**
```
git@github.com:VOTRE_USERNAME/projet-bibliotheque-inf1163.git
```

Ajoutez le dépôt distant :
```bash
git remote add origin https://github.com/VOTRE_USERNAME/projet-bibliotheque-inf1163.git
```

Vérifiez que c'est bien ajouté :
```bash
git remote -v
```

---

## 📤 Étape 8 : Pousser le code vers GitHub

### 8.1 Pousser la branche principale
```bash
git branch -M main
git push -u origin main
```

**OU si votre branche s'appelle "master" :**
```bash
git branch -M master
git push -u origin master
```

### 8.2 Entrer vos identifiants
- **Username** : Votre nom d'utilisateur GitHub
- **Password** : Votre **Personal Access Token** (PAS votre mot de passe GitHub)

> ⚠️ **Important** : GitHub n'accepte plus les mots de passe. Vous devez créer un **Personal Access Token** :
> 1. GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
> 2. Generate new token (classic)
> 3. Donnez-lui un nom et cochez les permissions nécessaires
> 4. Copiez le token (vous ne le reverrez plus !)
> 5. Utilisez ce token comme mot de passe

---

## 🔄 Commandes utiles pour la suite

### Voir l'état des fichiers
```bash
git status
```

### Ajouter des modifications
```bash
git add .
git commit -m "Description des modifications"
git push
```

### Voir l'historique
```bash
git log
```

### Cloner un dépôt (pour vos coéquipiers)
```bash
git clone https://github.com/VOTRE_USERNAME/projet-bibliotheque-inf1163.git
```

---

## 📋 Checklist rapide

- [ ] Git installé (`git --version`)
- [ ] Dépôt initialisé (`git init`)
- [ ] Git configuré (nom et email)
- [ ] Fichiers ajoutés (`git add .`)
- [ ] Premier commit créé (`git commit`)
- [ ] Dépôt créé sur GitHub
- [ ] Dépôt distant ajouté (`git remote add origin`)
- [ ] Code poussé (`git push -u origin main`)

---

## 🎯 Commandes en une seule fois (copier-coller)

```bash
# 1. Initialiser
cd "c:\Users\claud\Downloads\MODELISATION2"
git init

# 2. Ajouter les fichiers
git add src/ data/ docs/ bibliotheque.jar MANIFEST.MF *.md .gitignore plantuml.jar

# 3. Premier commit
git commit -m "Initial commit - Projet système de bibliothèque INF1163"

# 4. Ajouter le dépôt distant (remplacez l'URL par la vôtre)
git remote add origin https://github.com/VOTRE_USERNAME/projet-bibliotheque-inf1163.git

# 5. Pousser
git branch -M main
git push -u origin main
```

---

## ⚠️ Fichiers à NE PAS pousser (déjà dans .gitignore)

Les fichiers suivants seront automatiquement ignorés grâce à `.gitignore` :
- `bin/` - Fichiers compilés
- `build/` - Fichiers compilés
- `.project` - Configuration IDE
- `.classpath` - Configuration IDE
- `dist/` - Fichiers temporaires
- `data/emprunts.log` - Log

---

## 🆘 Problèmes courants

### Erreur : "remote origin already exists"
```bash
git remote remove origin
git remote add origin https://github.com/VOTRE_USERNAME/projet-bibliotheque-inf1163.git
```

### Erreur : "failed to push some refs"
```bash
git pull origin main --allow-unrelated-histories
git push -u origin main
```

### Oublier d'ajouter un fichier
```bash
git add nom-du-fichier
git commit -m "Ajout du fichier"
git push
```

---

**Bon dépôt ! 🚀**

