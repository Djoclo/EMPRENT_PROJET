# Script PowerShell pour créer et pousser un dépôt Git
# Usage: .\creerDepot.ps1

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  CREATION ET PUSH D'UN DEPOT GIT" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Vérifier que Git est installé
try {
    $gitVersion = git --version
    Write-Host "✓ Git installe: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Git n'est pas installe!" -ForegroundColor Red
    Write-Host "Telechargez Git sur: https://git-scm.com/download/win" -ForegroundColor Yellow
    exit 1
}

Write-Host ""

# Vérifier si le dépôt est déjà initialisé
if (Test-Path ".git") {
    Write-Host "⚠ Le depot Git est deja initialise." -ForegroundColor Yellow
    $reinit = Read-Host "Voulez-vous reinitialiser? (o/n)"
    if ($reinit -eq "o" -or $reinit -eq "O") {
        Remove-Item -Recurse -Force .git
        Write-Host "Depot reinitialise." -ForegroundColor Green
    } else {
        Write-Host "Utilisation du depot existant." -ForegroundColor Green
    }
}

# Initialiser le dépôt si nécessaire
if (-not (Test-Path ".git")) {
    Write-Host "Initialisation du depot Git..." -ForegroundColor Cyan
    git init
    Write-Host "✓ Depot initialise" -ForegroundColor Green
}

Write-Host ""

# Vérifier la configuration Git
Write-Host "Verification de la configuration Git..." -ForegroundColor Cyan
$userName = git config --global user.name
$userEmail = git config --global user.email

if ([string]::IsNullOrEmpty($userName)) {
    Write-Host "⚠ Nom d'utilisateur Git non configure" -ForegroundColor Yellow
    $name = Read-Host "Entrez votre nom"
    git config --global user.name $name
}

if ([string]::IsNullOrEmpty($userEmail)) {
    Write-Host "⚠ Email Git non configure" -ForegroundColor Yellow
    $email = Read-Host "Entrez votre email"
    git config --global user.email $email
}

Write-Host "✓ Configuration: $userName <$userEmail>" -ForegroundColor Green
Write-Host ""

# Ajouter les fichiers
Write-Host "Ajout des fichiers au depot..." -ForegroundColor Cyan
git add src/ data/ docs/ bibliotheque.jar MANIFEST.MF *.md .gitignore plantuml.jar creerJar.ps1 2>&1 | Out-Null

# Vérifier ce qui sera commité
$status = git status --short
if ($status) {
    Write-Host "✓ Fichiers ajoutes:" -ForegroundColor Green
    git status --short
} else {
    Write-Host "⚠ Aucun fichier nouveau a ajouter" -ForegroundColor Yellow
}

Write-Host ""

# Créer le commit
$hasChanges = git diff --cached --quiet
if (-not $hasChanges) {
    Write-Host "Creation du commit..." -ForegroundColor Cyan
    git commit -m "Initial commit - Projet systeme de bibliotheque INF1163"
    Write-Host "✓ Commit cree" -ForegroundColor Green
} else {
    Write-Host "⚠ Aucun changement a commiter" -ForegroundColor Yellow
}

Write-Host ""

# Vérifier si un dépôt distant existe
$remote = git remote get-url origin 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "  ETAPE SUIVANTE: Creer un depot sur GitHub" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "1. Allez sur https://github.com" -ForegroundColor Yellow
    Write-Host "2. Creez un nouveau depot (New repository)" -ForegroundColor Yellow
    Write-Host "3. NE cochez PAS 'Initialize with README'" -ForegroundColor Yellow
    Write-Host "4. Copiez l'URL du depot (ex: https://github.com/USERNAME/repo.git)" -ForegroundColor Yellow
    Write-Host ""
    $repoUrl = Read-Host "Entrez l'URL de votre depot GitHub"
    
    if ($repoUrl) {
        git remote add origin $repoUrl
        Write-Host "✓ Depot distant ajoute: $repoUrl" -ForegroundColor Green
        
        Write-Host ""
        Write-Host "Pousser le code vers GitHub..." -ForegroundColor Cyan
        Write-Host "⚠ Vous devrez entrer vos identifiants GitHub" -ForegroundColor Yellow
        Write-Host "⚠ Utilisez un Personal Access Token comme mot de passe" -ForegroundColor Yellow
        Write-Host ""
        
        git branch -M main
        git push -u origin main
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host ""
            Write-Host "✓ Code pousse avec succes!" -ForegroundColor Green
        } else {
            Write-Host ""
            Write-Host "✗ Erreur lors du push. Verifiez vos identifiants." -ForegroundColor Red
        }
    }
} else {
    Write-Host "Depot distant deja configure: $remote" -ForegroundColor Green
    Write-Host ""
    $push = Read-Host "Voulez-vous pousser le code maintenant? (o/n)"
    if ($push -eq "o" -or $push -eq "O") {
        git branch -M main
        git push -u origin main
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  TERMINE!" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

