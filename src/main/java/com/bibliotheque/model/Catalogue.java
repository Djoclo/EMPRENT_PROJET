package com.bibliotheque.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant le catalogue de la bibliothèque.
 * Gère les livres et leurs exemplaires.
 */
public class Catalogue {
    private Map<Integer, Livre> livres;
    private Map<String, Exemplaire> exemplaires;
    
    public Catalogue() {
        this.livres = new HashMap<>();
        this.exemplaires = new HashMap<>();
    }
    
    // Méthodes de gestion des livres
    
    /**
     * Ajoute un livre au catalogue.
     * @param livre Le livre à ajouter
     */
    public void ajouterLivre(Livre livre) {
        livres.put(livre.getId(), livre);
    }
    
    /**
     * Recherche un livre par son identifiant.
     * @param id L'identifiant du livre
     * @return Le livre correspondant, ou null si non trouvé
     */
    public Livre getLivre(int id) {
        return livres.get(id);
    }
    
    /**
     * Retourne la liste de tous les livres du catalogue.
     * @return Une liste contenant tous les livres
     */
    public List<Livre> getTousLesLivres() {
        return new ArrayList<>(livres.values());
    }
    
    // Méthodes de gestion des exemplaires
    
    /**
     * Ajoute un exemplaire au catalogue.
     * @param exemplaire L'exemplaire à ajouter
     */
    public void ajouterExemplaire(Exemplaire exemplaire) {
        exemplaires.put(exemplaire.getRfid(), exemplaire);
    }
    
    /**
     * Recherche un exemplaire par son RFID.
     * @param rfid Le RFID de l'exemplaire (6 chiffres)
     * @return L'exemplaire correspondant, ou null si non trouvé
     */
    public Exemplaire getExemplaire(String rfid) {
        return exemplaires.get(rfid);
    }
    
    /**
     * Retourne la liste de tous les exemplaires disponibles.
     * @return Une liste contenant uniquement les exemplaires disponibles
     */
    public List<Exemplaire> getExemplairesDisponibles() {
        List<Exemplaire> disponibles = new ArrayList<>();
        for (Exemplaire ex : exemplaires.values()) {
            if (ex.isDisponible()) {
                disponibles.add(ex);
            }
        }
        return disponibles;
    }
    
    /**
     * Retourne tous les exemplaires d'un livre donné.
     * @param idLivre L'identifiant du livre
     * @return Une liste contenant tous les exemplaires du livre
     */
    public List<Exemplaire> getExemplairesParLivre(int idLivre) {
        List<Exemplaire> exemplairesLivre = new ArrayList<>();
        for (Exemplaire ex : exemplaires.values()) {
            if (ex.getLivre().getId() == idLivre) {
                exemplairesLivre.add(ex);
            }
        }
        return exemplairesLivre;
    }
    
    /**
     * Retourne le nombre total d'exemplaires dans le catalogue.
     * @return Le nombre d'exemplaires
     */
    public int getNombreExemplaires() {
        return exemplaires.size();
    }
    
    /**
     * Retourne le nombre total de livres dans le catalogue.
     * @return Le nombre de livres
     */
    public int getNombreLivres() {
        return livres.size();
    }
}



