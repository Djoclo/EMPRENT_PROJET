package com.bibliotheque.model;

import java.time.LocalDate;

/**
 * Classe représentant un exemplaire physique d'un livre.
 * Chaque exemplaire possède un RFID unique et est associé à un livre.
 */
public class Exemplaire {
    private String rfid;
    private Livre livre;
    private boolean disponible;
    private LocalDate dateEmprunt;
    private Usager usagerEmprunt;
    
    public Exemplaire(String rfid, Livre livre) {
        this.rfid = rfid;
        this.livre = livre;
        this.disponible = true;
        this.dateEmprunt = null;
        this.usagerEmprunt = null;
    }
    
    // Getters
    
    /**
     * Retourne le RFID de l'exemplaire.
     * @return Le RFID (6 chiffres)
     */
    public String getRfid() {
        return rfid;
    }
    
    /**
     * Retourne le livre associé à cet exemplaire.
     * @return Le livre
     */
    public Livre getLivre() {
        return livre;
    }
    
    /**
     * Vérifie si l'exemplaire est disponible pour l'emprunt.
     * @return true si disponible, false si déjà emprunté
     */
    public boolean isDisponible() {
        return disponible;
    }
    
    /**
     * Retourne la date à laquelle l'exemplaire a été emprunté.
     * @return La date d'emprunt, ou null si l'exemplaire est disponible
     */
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    
    /**
     * Retourne l'usager qui a emprunté cet exemplaire.
     * @return L'usager, ou null si l'exemplaire est disponible
     */
    public Usager getUsagerEmprunt() {
        return usagerEmprunt;
    }
    
    // Méthodes métier
    
    /**
     * Marque l'exemplaire comme emprunté par un usager.
     * Ne fait rien si l'exemplaire n'est pas disponible.
     * @param usager L'usager qui emprunte
     * @param date La date d'emprunt
     */
    public void emprunter(Usager usager, LocalDate date) {
        if (disponible) {
            this.disponible = false;
            this.dateEmprunt = date;
            this.usagerEmprunt = usager;
        }
    }
    
    /**
     * Marque l'exemplaire comme retourné (disponible).
     * Réinitialise la date d'emprunt et l'usager.
     */
    public void retourner() {
        this.disponible = true;
        this.dateEmprunt = null;
        this.usagerEmprunt = null;
    }
    
    @Override
    public String toString() {
        return "Exemplaire RFID: " + rfid + " - " + livre.getTitre();
    }
}



