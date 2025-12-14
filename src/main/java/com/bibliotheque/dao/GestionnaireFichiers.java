package com.bibliotheque.dao;

import com.bibliotheque.model.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Classe responsable de la gestion des fichiers CSV (base de données simplifiée).
 * Conformément aux recommandations du professeur, les fichiers Excel (CSV) 
 * servent de base de données simplifiée.
 */
public class GestionnaireFichiers {
    private static final String DOSSIER_DATA = "data";
    private static final String FICHIER_USAGERS = "usagers.csv";
    private static final String FICHIER_LIVRES = "livres.csv";
    private static final String FICHIER_EXEMPLAIRES = "exemplaires.csv";
    
    /**
     * Charge les usagers depuis le fichier CSV.
     * @return Une liste contenant tous les usagers chargés
     * @throws IOException Si le fichier est introuvable ou illisible
     */
    public List<Usager> chargerUsagers() throws IOException {
        List<Usager> usagers = new ArrayList<>();
        Path chemin = Paths.get(DOSSIER_DATA, FICHIER_USAGERS);
        
        if (!Files.exists(chemin)) {
            throw new FileNotFoundException("Fichier usagers.csv introuvable dans " + DOSSIER_DATA);
        }
        
        try (BufferedReader reader = Files.newBufferedReader(chemin)) {
            String ligne = reader.readLine(); // Lire l'en-tête
            
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                if (champs.length == 3) {
                    String numeroCompte = champs[0].trim();
                    String nip = champs[1].trim();
                    String nom = champs[2].trim();
                    usagers.add(new Usager(numeroCompte, nip, nom));
                }
            }
        }
        
        return usagers;
    }
    
    /**
     * Charge les livres depuis le fichier CSV.
     * @return Une liste contenant tous les livres chargés
     * @throws IOException Si le fichier est introuvable ou illisible
     */
    public List<Livre> chargerLivres() throws IOException {
        List<Livre> livres = new ArrayList<>();
        Path chemin = Paths.get(DOSSIER_DATA, FICHIER_LIVRES);
        
        if (!Files.exists(chemin)) {
            throw new FileNotFoundException("Fichier livres.csv introuvable dans " + DOSSIER_DATA);
        }
        
        try (BufferedReader reader = Files.newBufferedReader(chemin)) {
            String ligne = reader.readLine(); // Lire l'en-tête
            
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                if (champs.length == 6) {
                    int id = Integer.parseInt(champs[0].trim());
                    String titre = champs[1].trim();
                    String auteur = champs[2].trim();
                    String edition = champs[3].trim();
                    int annee = Integer.parseInt(champs[4].trim());
                    int pages = Integer.parseInt(champs[5].trim());
                    livres.add(new Livre(id, titre, auteur, edition, annee, pages));
                }
            }
        }
        
        return livres;
    }
    
    /**
     * Charge les exemplaires depuis le fichier CSV.
     * Nécessite que les livres soient déjà chargés dans le catalogue.
     * @param catalogue Le catalogue contenant les livres (pour associer les exemplaires)
     * @return Une liste contenant tous les exemplaires chargés
     * @throws IOException Si le fichier est introuvable ou illisible
     */
    public List<Exemplaire> chargerExemplaires(Catalogue catalogue) throws IOException {
        List<Exemplaire> exemplaires = new ArrayList<>();
        Path chemin = Paths.get(DOSSIER_DATA, FICHIER_EXEMPLAIRES);
        
        if (!Files.exists(chemin)) {
            throw new FileNotFoundException("Fichier exemplaires.csv introuvable dans " + DOSSIER_DATA);
        }
        
        try (BufferedReader reader = Files.newBufferedReader(chemin)) {
            String ligne = reader.readLine(); // Lire l'en-tête
            
            while ((ligne = reader.readLine()) != null) {
                String[] champs = ligne.split(",");
                if (champs.length == 2) {
                    String rfid = champs[0].trim();
                    int idLivre = Integer.parseInt(champs[1].trim());
                    Livre livre = catalogue.getLivre(idLivre);
                    
                    if (livre != null) {
                        exemplaires.add(new Exemplaire(rfid, livre));
                    }
                }
            }
        }
        
        return exemplaires;
    }
    
    /**
     * Sauvegarde un nouvel emprunt dans un fichier de log (optionnel).
     */
    public void sauvegarderEmprunt(Emprunt emprunt) throws IOException {
        Path chemin = Paths.get(DOSSIER_DATA, "emprunts.log");
        
        try (BufferedWriter writer = Files.newBufferedWriter(chemin, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(String.format("%s,%s,%s,%s%n",
                emprunt.getUsager().getNumeroCompte(),
                emprunt.getExemplaire().getRfid(),
                emprunt.getDateEmprunt(),
                emprunt.getDateRetourPrevue()));
        }
    }
}



