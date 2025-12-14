package com.bibliotheque.model;

/**
 * Classe représentant un livre dans le système de bibliothèque.
 * Correspond à la couche métier du système.
 * Un livre contient les informations bibliographiques de base.
 */
public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String edition;
    private int annee;
    private int pages;
    
    public Livre(int id, String titre, String auteur, String edition, int annee, int pages) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.annee = annee;
        this.pages = pages;
    }
    
    // Getters
    
    /**
     * Retourne l'identifiant unique du livre.
     * @return L'identifiant du livre
     */
    public int getId() {
        return id;
    }
    
    /**
     * Retourne le titre du livre.
     * @return Le titre
     */
    public String getTitre() {
        return titre;
    }
    
    /**
     * Retourne le nom de l'auteur.
     * @return Le nom de l'auteur
     */
    public String getAuteur() {
        return auteur;
    }
    
    /**
     * Retourne l'édition du livre.
     * @return Le numéro d'édition
     */
    public String getEdition() {
        return edition;
    }
    
    /**
     * Retourne l'année de publication.
     * @return L'année de publication
     */
    public int getAnnee() {
        return annee;
    }
    
    /**
     * Retourne le nombre de pages.
     * @return Le nombre de pages
     */
    public int getPages() {
        return pages;
    }
    
    @Override
    public String toString() {
        return titre + " - " + auteur + " (" + annee + ")";
    }
}



