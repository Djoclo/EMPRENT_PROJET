package com.bibliotheque.controller;

import com.bibliotheque.model.*;
import com.bibliotheque.dao.GestionnaireFichiers;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur gérant les opérations d'emprunt de documents.
 * Fait partie de la couche contrôleur dans l'architecture MVC.
 */
public class ControleurEmprunt {
    private Catalogue catalogue;
    private Map<String, Usager> usagers;
    private Map<Exemplaire, Emprunt> empruntsActifs;
    private GestionnaireFichiers gestionnaireFichiers;
    
    public ControleurEmprunt(Catalogue catalogue, Map<String, Usager> usagers) {
        this.catalogue = catalogue;
        this.usagers = usagers;
        this.empruntsActifs = new HashMap<>();
        this.gestionnaireFichiers = new GestionnaireFichiers();
    }
    
    /**
     * Authentifie un usager par son numéro de compte et son NIP.
     * @param numeroCompte Le numéro de compte de l'usager
     * @param nip Le NIP saisi par l'usager (4 chiffres)
     * @return L'usager authentifié, ou null si l'authentification échoue
     */
    public Usager authentifierUsager(String numeroCompte, String nip) {
        Usager usager = usagers.get(numeroCompte);
        if (usager != null && usager.verifierNip(nip)) {
            return usager;
        }
        return null;
    }
    
    /**
     * Trouve un exemplaire par son RFID dans le catalogue.
     * @param rfid Le RFID de l'exemplaire (6 chiffres)
     * @return L'exemplaire correspondant, ou null si non trouvé
     */
    public Exemplaire trouverExemplaire(String rfid) {
        return catalogue.getExemplaire(rfid);
    }
    
    /**
     * Vérifie si un exemplaire est disponible pour l'emprunt.
     * @param exemplaire L'exemplaire à vérifier
     * @return true si l'exemplaire existe et est disponible, false sinon
     */
    public boolean estDisponible(Exemplaire exemplaire) {
        return exemplaire != null && exemplaire.isDisponible();
    }
    
    /**
     * Crée un emprunt pour un usager et un exemplaire.
     * Met à jour l'état de l'exemplaire et enregistre l'emprunt dans le fichier de log.
     * @param usager L'usager qui emprunte
     * @param exemplaire L'exemplaire à emprunter
     * @return L'objet Emprunt créé
     * @throws Exception Si l'exemplaire n'est pas disponible
     */
    public Emprunt emprunter(Usager usager, Exemplaire exemplaire) throws Exception {
        // Vérifier que l'exemplaire est disponible
        if (!estDisponible(exemplaire)) {
            throw new Exception("Cet exemplaire n'est pas disponible pour l'emprunt.");
        }
        
        // Créer l'emprunt
        LocalDate dateEmprunt = LocalDate.now();
        Emprunt emprunt = new Emprunt(exemplaire, usager, dateEmprunt);
        
        // Mettre à jour l'exemplaire
        exemplaire.emprunter(usager, dateEmprunt);
        
        // Enregistrer l'emprunt
        usager.ajouterEmprunt(emprunt);
        empruntsActifs.put(exemplaire, emprunt);
        
        // Sauvegarder dans le fichier (log)
        try {
            gestionnaireFichiers.sauvegarderEmprunt(emprunt);
        } catch (Exception e) {
            // Log l'erreur mais ne bloque pas l'emprunt
            System.err.println("Erreur lors de la sauvegarde de l'emprunt: " + e.getMessage());
        }
        
        return emprunt;
    }
    
    /**
     * Retourne un exemplaire emprunté (marque l'exemplaire comme disponible).
     * Retire l'emprunt de la liste des emprunts actifs de l'usager.
     * @param exemplaire L'exemplaire à retourner
     */
    public void retourner(Exemplaire exemplaire) {
        Emprunt emprunt = empruntsActifs.remove(exemplaire);
        if (emprunt != null) {
            exemplaire.retourner();
            emprunt.getUsager().retirerEmprunt(emprunt);
        }
    }
    
    /**
     * Obtient la liste des emprunts actifs d'un usager.
     * @param usager L'usager dont on veut connaître les emprunts
     * @return Une liste contenant tous les emprunts actifs de l'usager
     */
    public java.util.List<Emprunt> getEmpruntsUsager(Usager usager) {
        return usager.getEmprunts();
    }
}



