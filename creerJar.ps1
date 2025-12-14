# Script PowerShell pour créer le JAR
$currentDir = Get-Location

# Chercher jar.exe
$jarPath = $null
$possiblePaths = @(
    "$env:JAVA_HOME\bin\jar.exe",
    "C:\Program Files\Java\jdk*\bin\jar.exe",
    "C:\Program Files (x86)\Java\jdk*\bin\jar.exe"
)

foreach ($path in $possiblePaths) {
    $found = Get-ChildItem $path -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($found) {
        $jarPath = $found.FullName
        break
    }
}

if (-not $jarPath) {
    # Chercher dans le PATH
    try {
        $javaCmd = Get-Command java -ErrorAction Stop
        $javaDir = Split-Path $javaCmd.Source
        $jarPath = Join-Path $javaDir "jar.exe"
        if (-not (Test-Path $jarPath)) {
            throw "jar.exe introuvable"
        }
    } catch {
        Write-Host "ERREUR: jar.exe introuvable. Assurez-vous que le JDK est installe."
        exit 1
    }
}

Write-Host "Utilisation de: $jarPath"

# Créer le JAR
Set-Location build
& $jarPath cvfm ..\bibliotheque.jar ..\MANIFEST.MF com
Set-Location ..

if (Test-Path "bibliotheque.jar") {
    Write-Host "SUCCES: bibliotheque.jar cree!"
    Remove-Item "creerJar.ps1" -ErrorAction SilentlyContinue
    Remove-Item "creerJar.bat" -ErrorAction SilentlyContinue
} else {
    Write-Host "ERREUR: Echec de la creation du JAR"
    exit 1
}



