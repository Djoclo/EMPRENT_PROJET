package com.bibliotheque.system;

import com.bibliotheque.model.*;
import com.bibliotheque.controller.ControleurEmprunt;
import com.bibliotheque.dao.GestionnaireFichiers;
import java.util.*;

/**
 * Classe principale du système de bibliothèque.
 * Coordonne l'initialisation et les opérations du système.
 */
public class SystemeBibliotheque {
    private Catalogue catalogue;
    private Map<String, Usager> usagers;
    private ControleurEmprunt controleurEmprunt;
    private GestionnaireFichiers gestionnaireFichiers;
    private boolean initialise;
    
    public SystemeBibliotheque() {
        this.catalogue = new Catalogue();
        this.usagers = new HashMap<>();
        this.gestionnaireFichiers = new GestionnaireFichiers();
        this.initialise = false;
    }
    
    /**
     * Démarre le système en chargeant toutes les données depuis les fichiers CSV.
     * Charge les livres, exemplaires et usagers, puis initialise le contrôleur d'emprunt.
     * @throws Exception Si une erreur survient lors du chargement des fichiers CSV
     */
    public void demarrer() throws Exception {
        if (initialise) {
            return; // Déjà initialisé
        }
        
        try {
            // Charger les livres
            List<Livre> livres = gestionnaireFichiers.chargerLivres();
            for (Livre livre : livres) {
                catalogue.ajouterLivre(livre);
            }
            
            // Charger les exemplaires (nécessite les livres)
            List<Exemplaire> exemplaires = gestionnaireFichiers.chargerExemplaires(catalogue);
            for (Exemplaire exemplaire : exemplaires) {
                catalogue.ajouterExemplaire(exemplaire);
            }
            
            // Charger les usagers
            List<Usager> listeUsagers = gestionnaireFichiers.chargerUsagers();
            for (Usager usager : listeUsagers) {
                usagers.put(usager.getNumeroCompte(), usager);
            }
            
            // Initialiser le contrôleur
            controleurEmprunt = new ControleurEmprunt(catalogue, usagers);
            
            this.initialise = true;
            
            System.out.println("✓ Système démarré avec succès");
            System.out.println("  - Livres chargés: " + catalogue.getNombreLivres());
            System.out.println("  - Exemplaires chargés: " + catalogue.getNombreExemplaires());
            System.out.println("  - Usagers chargés: " + usagers.size());
            
        } catch (Exception e) {
            throw new Exception("Erreur lors du démarrage du système: " + e.getMessage(), e);
        }
    }
    
    // Getters
    
    /**
     * Retourne le catalogue de la bibliothèque.
     * @return Le catalogue contenant les livres et exemplaires
     */
    public Catalogue getCatalogue() {
        return catalogue;
    }
    
    /**
     * Retourne la map des usagers (clé = numéro de compte).
     * @return La map des usagers
     */
    public Map<String, Usager> getUsagers() {
        return usagers;
    }
    
    /**
     * Retourne le contrôleur d'emprunt.
     * @return Le contrôleur d'emprunt
     */
    public ControleurEmprunt getControleurEmprunt() {
        return controleurEmprunt;
    }
    
    /**
     * Vérifie si le système est initialisé.
     * @return true si le système a été démarré avec succès, false sinon
     */
    public boolean estInitialise() {
        return initialise;
    }
}

