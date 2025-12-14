package com.bibliotheque.model;

import java.time.LocalDate;

/**
 * Classe représentant un emprunt d'un exemplaire par un usager.
 * La durée de prêt est fixée à 21 jours selon les règles de gestion.
 */
public class Emprunt {
    private static final int DUREE_PRET_JOURS = 21;
    
    private Exemplaire exemplaire;
    private Usager usager;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    
    public Emprunt(Exemplaire exemplaire, Usager usager, LocalDate dateEmprunt) {
        this.exemplaire = exemplaire;
        this.usager = usager;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateEmprunt.plusDays(DUREE_PRET_JOURS);
    }
    
    // Getters
    
    /**
     * Retourne l'exemplaire emprunté.
     * @return L'exemplaire
     */
    public Exemplaire getExemplaire() {
        return exemplaire;
    }
    
    /**
     * Retourne l'usager qui a effectué l'emprunt.
     * @return L'usager
     */
    public Usager getUsager() {
        return usager;
    }
    
    /**
     * Retourne la date à laquelle l'emprunt a été effectué.
     * @return La date d'emprunt
     */
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    
    /**
     * Retourne la date à laquelle l'exemplaire doit être retourné.
     * Calculée automatiquement : dateEmprunt + 21 jours.
     * @return La date de retour prévue
     */
    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }
    
    /**
     * Retourne la durée standard de prêt en jours.
     * @return La durée de prêt (21 jours)
     */
    public int getDureePretJours() {
        return DUREE_PRET_JOURS;
    }
    
    /**
     * Vérifie si l'emprunt est en retard par rapport à la date actuelle.
     * @param dateActuelle La date actuelle à comparer
     * @return true si la date actuelle est après la date de retour prévue
     */
    public boolean estEnRetard(LocalDate dateActuelle) {
        return dateActuelle.isAfter(dateRetourPrevue);
    }
    
    @Override
    public String toString() {
        return exemplaire.getLivre().getTitre() + " - Emprunté le " + dateEmprunt + 
               " - Retour prévu le " + dateRetourPrevue;
    }
}



