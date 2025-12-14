package com.bibliotheque;

import com.bibliotheque.system.SystemeBibliotheque;
import com.bibliotheque.ui.InterfaceUtilisateur;
import javax.swing.*;

/**
 * Classe principale de l'application.
 * Point d'entrée du système de bibliothèque.
 */
public class Main {
    public static void main(String[] args) {
        // Utiliser le look and feel système
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Utiliser le look par défaut en cas d'erreur
        }
        
        // Créer et démarrer le système
        SystemeBibliotheque systeme = new SystemeBibliotheque();
        
        try {
            systeme.demarrer();
            
            // Créer et afficher l'interface utilisateur
            SwingUtilities.invokeLater(() -> {
                InterfaceUtilisateur interfaceUI = new InterfaceUtilisateur(systeme);
                interfaceUI.setVisible(true);
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Erreur lors du démarrage du système:\n" + e.getMessage(),
                "Erreur de démarrage",
                JOptionPane.ERROR_MESSAGE);
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



