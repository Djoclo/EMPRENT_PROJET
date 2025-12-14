package com.bibliotheque.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un usager de la bibliothèque.
 * Un usager possède un numéro de compte, un NIP et peut avoir des emprunts.
 */
public class Usager {
    private String numeroCompte;
    private String nip;
    private String nom;
    private List<Emprunt> emprunts;
    
    public Usager(String numeroCompte, String nip, String nom) {
        this.numeroCompte = numeroCompte;
        this.nip = nip;
        this.nom = nom;
        this.emprunts = new ArrayList<>();
    }
    
    // Getters
    
    /**
     * Retourne le numéro de compte de l'usager.
     * @return Le numéro de compte
     */
    public String getNumeroCompte() {
        return numeroCompte;
    }
    
    /**
     * Retourne le NIP de l'usager (pour authentification interne uniquement).
     * @return Le NIP (4 chiffres)
     */
    public String getNip() {
        return nip;
    }
    
    /**
     * Retourne le nom complet de l'usager.
     * @return Le nom de l'usager
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Retourne une copie de la liste des emprunts de l'usager.
     * @return Une nouvelle liste contenant les emprunts
     */
    public List<Emprunt> getEmprunts() {
        return new ArrayList<>(emprunts);
    }
    
    // Méthodes métier
    
    /**
     * Vérifie si le NIP saisi correspond au NIP de l'usager.
     * @param nipSaisi Le NIP saisi par l'usager
     * @return true si le NIP est correct, false sinon
     */
    public boolean verifierNip(String nipSaisi) {
        return this.nip.equals(nipSaisi);
    }
    
    /**
     * Ajoute un emprunt à la liste des emprunts de l'usager.
     * @param emprunt L'emprunt à ajouter
     */
    public void ajouterEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
    }
    
    /**
     * Retire un emprunt de la liste des emprunts de l'usager.
     * @param emprunt L'emprunt à retirer
     */
    public void retirerEmprunt(Emprunt emprunt) {
        emprunts.remove(emprunt);
    }
    
    /**
     * Retourne le nombre d'emprunts actifs de l'usager.
     * @return Le nombre d'emprunts
     */
    public int getNombreEmprunts() {
        return emprunts.size();
    }
    
    @Override
    public String toString() {
        return nom + " (" + numeroCompte + ")";
    }
}



