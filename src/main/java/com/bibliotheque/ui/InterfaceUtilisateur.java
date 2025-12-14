package com.bibliotheque.ui;

import com.bibliotheque.model.*;
import com.bibliotheque.system.SystemeBibliotheque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface utilisateur graphique pour le système de bibliothèque.
 * Interface Swing avec clavier virtuel et affichage amélioré.
 */
public class InterfaceUtilisateur extends JFrame {
    private SystemeBibliotheque systeme;
    private Usager usagerConnecte;
    
    // Composants principaux
    private JPanel panelPrincipal;
    private CardLayout cardLayout; // Pour changer entre les écrans
    
    // Écran 1: Connexion avec carte
    private JPanel panelConnexion;
    private JLabel labelImageCarte;
    private JTextField champNumeroCompte;
    private JPasswordField champNip;
    private JPanel panelClavier;
    private JTextField champFocusActif;
    
    // Écran 2: Emprunt
    private JPanel panelEmprunt;
    private JTextField champRfid;
    private JList<String> listeExemplaires;
    private DefaultListModel<String> listeModel;
    private java.util.List<Exemplaire> exemplairesAEmprunter;
    private JTextArea zoneAffichage;
    private JLabel labelStatut;
    
    // Écran 3: Récapitulatif et choix de reçu
    private JPanel panelRecu;
    
    public InterfaceUtilisateur(SystemeBibliotheque systeme) {
        this.systeme = systeme;
        this.exemplairesAEmprunter = new ArrayList<>();
        initialiserInterface();
    }
    
    private void initialiserInterface() {
        setTitle("Borne de Prêt Libre-Service - Bibliothèque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Panel principal avec CardLayout pour changer d'écran
        panelPrincipal = new JPanel();
        cardLayout = new CardLayout();
        panelPrincipal.setLayout(cardLayout);
        panelPrincipal.setBackground(new Color(245, 245, 250));
        
        // Créer les différents écrans
        panelConnexion = creerEcranConnexion();
        panelEmprunt = creerEcranEmprunt();
        panelRecu = creerEcranRecu();
        
        // Ajouter les écrans
        panelPrincipal.add(panelConnexion, "CONNEXION");
        panelPrincipal.add(panelEmprunt, "EMPRUNT");
        panelPrincipal.add(panelRecu, "RECU");
        
        add(panelPrincipal);
        
        // Afficher l'écran de connexion
        cardLayout.show(panelPrincipal, "CONNEXION");
    }
    
    /**
     * Crée l'écran de connexion avec zone d'image pour la carte et clavier virtuel
     */
    private JPanel creerEcranConnexion() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(245, 245, 250));
        
        // En-tête
        JPanel panelEnTete = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEnTete.setBackground(new Color(70, 130, 180));
        JLabel titre = new JLabel("📚 Bibliothèque - Prêt Libre-Service");
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelEnTete.add(titre);
        
        // Zone centrale
        JPanel panelCentre = new JPanel(new BorderLayout(20, 20));
        
        // Zone d'image pour la carte de bibliothèque
        JPanel panelImageCarte = new JPanel(new BorderLayout());
        panelImageCarte.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            "Placez votre carte de bibliothèque sous la ligne de balayage",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font(Font.SANS_SERIF, Font.BOLD, 14),
            new Color(70, 130, 180)
        ));
        panelImageCarte.setBackground(Color.WHITE);
        panelImageCarte.setPreferredSize(new Dimension(400, 200));
        
        // Zone d'image (simulation d'une carte de bibliothèque)
        labelImageCarte = new JLabel();
        labelImageCarte.setHorizontalAlignment(SwingConstants.CENTER);
        labelImageCarte.setVerticalAlignment(SwingConstants.CENTER);
        labelImageCarte.setOpaque(true);
        labelImageCarte.setBackground(new Color(240, 240, 240));
        labelImageCarte.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Dessiner une représentation visuelle de la zone de balayage
        labelImageCarte.setIcon(creerIconeCarteBibliotheque());
        
        JLabel instructionCarte = new JLabel(
            "<html><div style='text-align: center;'>" +
            "<p style='font-size: 12px; color: #666;'>" +
            "Glissez votre carte de bibliothèque sous la ligne<br>" +
            "ou utilisez les champs ci-dessous pour vous connecter" +
            "</p></div></html>"
        );
        instructionCarte.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelImageCarte.add(labelImageCarte, BorderLayout.CENTER);
        panelImageCarte.add(instructionCarte, BorderLayout.SOUTH);
        
        // Zone de connexion manuelle
        JPanel panelConnexionManuelle = new JPanel(new GridBagLayout());
        panelConnexionManuelle.setBorder(BorderFactory.createTitledBorder("Connexion manuelle"));
        panelConnexionManuelle.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Numéro de compte
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelConnexionManuelle.add(new JLabel("Numéro de compte:"), gbc);
        gbc.gridx = 1;
        champNumeroCompte = new JTextField(15);
        champNumeroCompte.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        champNumeroCompte.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                champFocusActif = champNumeroCompte;
            }
        });
        panelConnexionManuelle.add(champNumeroCompte, gbc);
        
        // NIP
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelConnexionManuelle.add(new JLabel("NIP (4 chiffres):"), gbc);
        gbc.gridx = 1;
        champNip = new JPasswordField(15);
        champNip.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        champNip.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                champFocusActif = champNip;
            }
        });
        panelConnexionManuelle.add(champNip, gbc);
        
        // Bouton connexion
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton btnConnexion = new JButton("Se connecter");
        btnConnexion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnConnexion.setBackground(Color.WHITE);
        btnConnexion.setForeground(new Color(70, 130, 180)); // Bleu comme l'en-tête
        btnConnexion.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnConnexion.addActionListener(e -> connecterUsager());
        panelConnexionManuelle.add(btnConnexion, gbc);
        
        // Clavier virtuel numérique
        panelClavier = creerClavierNumerique();
        
        // Assemblage
        JPanel panelGauche = new JPanel(new BorderLayout(10, 10));
        panelGauche.add(panelImageCarte, BorderLayout.NORTH);
        panelGauche.add(panelConnexionManuelle, BorderLayout.CENTER);
        
        panelCentre.add(panelGauche, BorderLayout.WEST);
        panelCentre.add(panelClavier, BorderLayout.CENTER);
        
        panel.add(panelEnTete, BorderLayout.NORTH);
        panel.add(panelCentre, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crée une icône représentant une carte de bibliothèque
     */
    private Icon creerIconeCarteBibliotheque() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fond de la carte
                g2d.setColor(new Color(255, 255, 255));
                g2d.fillRoundRect(x + 10, y + 10, getIconWidth() - 20, getIconHeight() - 20, 10, 10);
                
                // Bordure
                g2d.setColor(new Color(70, 130, 180));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(x + 10, y + 10, getIconWidth() - 20, getIconHeight() - 20, 10, 10);
                
                // Ligne de balayage (simulation RFID)
                g2d.setColor(new Color(200, 200, 200));
                g2d.setStroke(new BasicStroke(3));
                int ligneY = y + getIconHeight() / 2;
                g2d.drawLine(x + 30, ligneY, x + getIconWidth() - 30, ligneY);
                
                // Texte
                g2d.setColor(new Color(100, 100, 100));
                g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
                FontMetrics fm = g2d.getFontMetrics();
                String texte = "CARTE DE BIBLIOTHÈQUE";
                int texteX = x + (getIconWidth() - fm.stringWidth(texte)) / 2;
                g2d.drawString(texte, texteX, ligneY - 15);
                
                // Icône livre
                g2d.setColor(new Color(70, 130, 180));
                g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
                g2d.drawString("📚", x + getIconWidth() / 2 - 12, ligneY + 25);
                
                g2d.dispose();
            }
            
            @Override
            public int getIconWidth() {
                return 350;
            }
            
            @Override
            public int getIconHeight() {
                return 150;
            }
        };
    }
    
    /**
     * Crée un clavier virtuel numérique selon le modèle de la borne
     */
    private JPanel creerClavierNumerique() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Clavier numérique"));
        panel.setBackground(Color.WHITE);
        
        // Panel principal pour les nombres (grille 3x3 + 0)
        JPanel panelNombres = new JPanel(new GridBagLayout());
        panelNombres.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Grille 3x3 pour les nombres 1-9
        String[][] grille = {
            {"7", "8", "9"},
            {"4", "5", "6"},
            {"1", "2", "3"}
        };
        
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                
                final String chiffre = grille[i][j];
                JButton bouton = new JButton(chiffre);
                bouton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                bouton.setPreferredSize(new Dimension(70, 60));
                bouton.setBackground(Color.BLACK);
                bouton.setForeground(Color.WHITE);
                bouton.addActionListener(e -> {
                    if (champFocusActif != null) {
                        String texte = champFocusActif.getText();
                        if (champFocusActif instanceof JPasswordField) {
                            ((JPasswordField) champFocusActif).setText(texte + chiffre);
                        } else {
                            champFocusActif.setText(texte + chiffre);
                        }
                    }
                });
                panelNombres.add(bouton, gbc);
            }
        }
        
        // Le 0 en bas à gauche
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JButton btnZero = new JButton("0");
        btnZero.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnZero.setPreferredSize(new Dimension(70, 60));
        btnZero.setBackground(Color.BLACK);
        btnZero.setForeground(Color.WHITE);
        btnZero.addActionListener(e -> {
            if (champFocusActif != null) {
                String texte = champFocusActif.getText();
                if (champFocusActif instanceof JPasswordField) {
                    ((JPasswordField) champFocusActif).setText(texte + "0");
                } else {
                    champFocusActif.setText(texte + "0");
                }
            }
        });
        panelNombres.add(btnZero, gbc);
        
        // Panel pour les boutons fonctionnels à droite
        JPanel panelFonctions = new JPanel(new GridBagLayout());
        panelFonctions.setBackground(Color.WHITE);
        GridBagConstraints gbcFonctions = new GridBagConstraints();
        gbcFonctions.insets = new Insets(5, 5, 5, 5);
        gbcFonctions.fill = GridBagConstraints.BOTH;
        gbcFonctions.weightx = 1.0;
        gbcFonctions.weighty = 1.0;
        
        // Bouton Effacer
        gbcFonctions.gridx = 0;
        gbcFonctions.gridy = 0;
        JButton btnEffacer = new JButton("Effacer");
        btnEffacer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnEffacer.setPreferredSize(new Dimension(100, 60));
        btnEffacer.setBackground(new Color(70, 130, 180)); // Bleu comme l'en-tête
        btnEffacer.setForeground(Color.WHITE);
        btnEffacer.addActionListener(e -> {
            if (champFocusActif != null) {
                champFocusActif.setText("");
            }
        });
        panelFonctions.add(btnEffacer, gbcFonctions);
        
        // Bouton Annuler
        gbcFonctions.gridy = 1;
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnAnnuler.setPreferredSize(new Dimension(100, 60));
        btnAnnuler.setBackground(Color.BLACK);
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.addActionListener(e -> {
            if (champFocusActif != null) {
                champFocusActif.setText("");
            }
            // Retour à l'écran de connexion si on est sur l'écran d'emprunt
            if (usagerConnecte != null) {
                int choix = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous annuler et vous déconnecter?",
                    "Annuler",
                    JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {
                    champNumeroCompte.setText("");
                    champNip.setText("");
                    usagerConnecte = null;
                    cardLayout.show(panelPrincipal, "CONNEXION");
                }
            }
        });
        panelFonctions.add(btnAnnuler, gbcFonctions);
        
        // Bouton Entrer
        gbcFonctions.gridy = 2;
        JButton btnEntrer = new JButton("Entrer");
        btnEntrer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnEntrer.setPreferredSize(new Dimension(100, 60));
        btnEntrer.setBackground(new Color(70, 130, 180)); // Bleu comme l'en-tête
        btnEntrer.setForeground(Color.WHITE);
        btnEntrer.addActionListener(e -> connecterUsager());
        panelFonctions.add(btnEntrer, gbcFonctions);
        
        // Assemblage : nombres à gauche, fonctions à droite
        panel.add(panelNombres, BorderLayout.CENTER);
        panel.add(panelFonctions, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Crée l'écran d'emprunt
     */
    private JPanel creerEcranEmprunt() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 250));
        
        // En-tête
        JPanel panelEnTete = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEnTete.setBackground(new Color(70, 130, 180));
        JLabel titre = new JLabel("📚 Emprunt de Documents");
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEnTete.add(titre);
        
        // Zone centrale
        JPanel panelCentre = new JPanel(new BorderLayout(10, 10));
        
        // Zone de saisie RFID
        JPanel panelSaisie = new JPanel(new GridBagLayout());
        panelSaisie.setBorder(BorderFactory.createTitledBorder("Saisir le RFID de l'exemplaire"));
        panelSaisie.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSaisie.add(new JLabel("RFID (6 chiffres):"), gbc);
        gbc.gridx = 1;
        champRfid = new JTextField(15);
        champRfid.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        panelSaisie.add(champRfid, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton btnAjouter = new JButton("Ajouter exemplaire");
        btnAjouter.setBackground(Color.WHITE);
        btnAjouter.setForeground(new Color(70, 130, 180)); // Bleu comme l'en-tête
        btnAjouter.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnAjouter.addActionListener(e -> ajouterExemplaire());
        panelSaisie.add(btnAjouter, gbc);
        
        gbc.gridy = 2;
        JButton btnFinaliser = new JButton("Finaliser les emprunts");
        btnFinaliser.setBackground(Color.WHITE);
        btnFinaliser.setForeground(new Color(70, 130, 180)); // Bleu comme l'en-tête
        btnFinaliser.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnFinaliser.addActionListener(e -> finaliserEmprunts());
        panelSaisie.add(btnFinaliser, gbc);
        
        // Liste des exemplaires saisis
        listeModel = new DefaultListModel<>();
        listeExemplaires = new JList<>(listeModel);
        listeExemplaires.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        JScrollPane scrollListe = new JScrollPane(listeExemplaires);
        scrollListe.setBorder(BorderFactory.createTitledBorder("Exemplaires à emprunter"));
        scrollListe.setPreferredSize(new Dimension(400, 200));
        
        // Zone d'affichage
        zoneAffichage = new JTextArea(15, 50);
        zoneAffichage.setEditable(false);
        zoneAffichage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        zoneAffichage.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(zoneAffichage);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Informations"));
        
        // Assemblage
        JPanel panelGauche = new JPanel(new BorderLayout(10, 10));
        panelGauche.add(panelSaisie, BorderLayout.NORTH);
        panelGauche.add(scrollListe, BorderLayout.CENTER);
        
        panelCentre.add(panelGauche, BorderLayout.WEST);
        panelCentre.add(scrollPane, BorderLayout.CENTER);
        
        // Statut
        labelStatut = new JLabel("Prêt à emprunter");
        labelStatut.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        labelStatut.setForeground(new Color(50, 50, 50));
        
        panel.add(panelEnTete, BorderLayout.NORTH);
        panel.add(panelCentre, BorderLayout.CENTER);
        panel.add(labelStatut, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Crée l'écran de récapitulatif et choix de reçu
     */
    private JPanel creerEcranRecu() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(245, 245, 250));
        
        // En-tête
        JPanel panelEnTete = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEnTete.setBackground(new Color(70, 130, 180));
        JLabel titre = new JLabel("📚 Récapitulatif de vos emprunts");
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEnTete.add(titre);
        
        // Zone centrale avec récapitulatif
        JPanel panelCentre = new JPanel(new BorderLayout(20, 20));
        
        // Récapitulatif (sera mis à jour dynamiquement)
        JTextArea recapitulatif = new JTextArea(10, 50);
        recapitulatif.setEditable(false);
        recapitulatif.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        recapitulatif.setBackground(Color.WHITE);
        recapitulatif.setBorder(BorderFactory.createTitledBorder("Vos emprunts"));
        JScrollPane scrollRecap = new JScrollPane(recapitulatif);
        
        // Options de reçu avec icônes
        JPanel panelOptionsRecu = new JPanel(new GridLayout(1, 3, 20, 20));
        panelOptionsRecu.setBorder(BorderFactory.createTitledBorder("Souhaitez-vous un reçu?"));
        panelOptionsRecu.setBackground(new Color(245, 245, 250));
        
        // Option 1: Reçu imprimé
        JButton btnImprime = new JButton("<html><div style='text-align: center;'>" +
            "<p style='font-size: 36px;'>🖨️</p>" +
            "<p style='font-size: 14px; font-weight: bold;'>Reçu imprimé</p>" +
            "</div></html>");
        btnImprime.setPreferredSize(new Dimension(200, 150));
        btnImprime.setBackground(new Color(255, 255, 255));
        btnImprime.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnImprime.addActionListener(e -> {
            traiterChoixRecu("IMPRIME", recapitulatif);
        });
        
        // Option 2: Reçu par courriel
        JButton btnCourriel = new JButton("<html><div style='text-align: center;'>" +
            "<p style='font-size: 36px;'>📧</p>" +
            "<p style='font-size: 14px; font-weight: bold;'>Reçu par courriel</p>" +
            "</div></html>");
        btnCourriel.setPreferredSize(new Dimension(200, 150));
        btnCourriel.setBackground(new Color(255, 255, 255));
        btnCourriel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnCourriel.addActionListener(e -> {
            traiterChoixRecu("COURRIEL", recapitulatif);
        });
        
        // Option 3: Pas de reçu
        JButton btnPasRecu = new JButton("<html><div style='text-align: center;'>" +
            "<p style='font-size: 36px;'>❌</p>" +
            "<p style='font-size: 14px; font-weight: bold;'>Pas de reçu</p>" +
            "</div></html>");
        btnPasRecu.setPreferredSize(new Dimension(200, 150));
        btnPasRecu.setBackground(new Color(255, 255, 255));
        btnPasRecu.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        btnPasRecu.addActionListener(e -> {
            traiterChoixRecu("PAS_RECU", recapitulatif);
        });
        
        panelOptionsRecu.add(btnImprime);
        panelOptionsRecu.add(btnCourriel);
        panelOptionsRecu.add(btnPasRecu);
        
        // Stocker la référence pour mise à jour
        panelOptionsRecu.putClientProperty("recapitulatif", recapitulatif);
        
        panelCentre.add(scrollRecap, BorderLayout.CENTER);
        panelCentre.add(panelOptionsRecu, BorderLayout.SOUTH);
        
        panel.add(panelEnTete, BorderLayout.NORTH);
        panel.add(panelCentre, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void connecterUsager() {
        String numeroCompte = champNumeroCompte.getText().trim();
        String nip = new String(champNip.getPassword());
        
        if (numeroCompte.isEmpty() || nip.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez saisir le numéro de compte et le NIP.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nip.length() != 4 || !nip.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, 
                "Le NIP doit contenir exactement 4 chiffres.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        usagerConnecte = systeme.getControleurEmprunt().authentifierUsager(numeroCompte, nip);
        
        if (usagerConnecte != null) {
            // Afficher l'écran d'emprunt
            cardLayout.show(panelPrincipal, "EMPRUNT");
            afficherMessage("Connexion réussie!\nBienvenue " + usagerConnecte.getNom() + 
                ".\n\nVeuillez saisir le RFID de l'exemplaire à emprunter.");
            mettreAJourStatut("Connecté: " + usagerConnecte.getNom());
        } else {
            JOptionPane.showMessageDialog(this, 
                "Numéro de compte ou NIP incorrect.", 
                "Erreur d'authentification", 
                JOptionPane.ERROR_MESSAGE);
            champNip.setText("");
        }
    }
    
    private void ajouterExemplaire() {
        if (usagerConnecte == null) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez vous connecter d'abord.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String rfid = champRfid.getText().trim();
        if (rfid.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez saisir le RFID de l'exemplaire.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (rfid.length() != 6 || !rfid.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(this, 
                "Le RFID doit contenir exactement 6 chiffres.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Exemplaire exemplaire = systeme.getControleurEmprunt().trouverExemplaire(rfid);
        if (exemplaire == null) {
            JOptionPane.showMessageDialog(this, 
                "Aucun exemplaire trouvé avec ce RFID: " + rfid, 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!systeme.getControleurEmprunt().estDisponible(exemplaire)) {
            JOptionPane.showMessageDialog(this, 
                "Cet exemplaire n'est pas disponible pour l'emprunt.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Vérifier si déjà ajouté
        if (exemplairesAEmprunter.contains(exemplaire)) {
            JOptionPane.showMessageDialog(this, 
                "Cet exemplaire a déjà été ajouté à la liste.", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Ajouter à la liste temporaire
        exemplairesAEmprunter.add(exemplaire);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String retour = LocalDate.now().plusDays(21).format(formatter);
        listeModel.addElement(exemplaire.getRfid() + " : " + exemplaire.getLivre().getTitre() + 
            " - Retour: " + retour);
        
        afficherMessage("Exemplaire ajouté: " + exemplaire.getLivre().getTitre() + 
            "\nTotal saisis: " + listeModel.size());
        champRfid.setText("");
    }
    
    private void finaliserEmprunts() {
        if (exemplairesAEmprunter == null || exemplairesAEmprunter.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Aucun exemplaire saisi. Ajoutez au moins un exemplaire avant de finaliser.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.util.List<Emprunt> empruntsCrees = new ArrayList<>();
        try {
            for (Exemplaire ex : exemplairesAEmprunter) {
                Emprunt emprunt = systeme.getControleurEmprunt().emprunter(usagerConnecte, ex);
                empruntsCrees.add(emprunt);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la finalisation: " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Afficher le récapitulatif et les options de reçu
        afficherResumeEtOptionsRecu(empruntsCrees);
        
        // Réinitialiser la saisie
        exemplairesAEmprunter.clear();
        listeModel.clear();
    }
    
    private void afficherResumeEtOptionsRecu(java.util.List<Emprunt> emprunts) {
        // Construire le récapitulatif avec formatage amélioré
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════\n");
        sb.append("     RÉCAPITULATIF DE VOS EMPRUNTS\n");
        sb.append("═══════════════════════════════════════════════\n\n");
        
        int nombreDocuments = emprunts.size();
        if (nombreDocuments == 0) {
            sb.append("Aucun document emprunté.\n");
        } else {
            sb.append("Nombre de documents empruntés: ").append(nombreDocuments);
            if (nombreDocuments == 1) {
                sb.append(" document\n");
            } else {
                sb.append(" documents\n");
            }
            sb.append("\n");
            sb.append("───────────────────────────────────────────────\n\n");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (int i = 0; i < emprunts.size(); i++) {
                Emprunt e = emprunts.get(i);
                sb.append("Document ").append(i + 1).append(":\n");
                sb.append("  • Titre: ").append(e.getExemplaire().getLivre().getTitre()).append("\n");
                sb.append("  • Auteur: ").append(e.getExemplaire().getLivre().getAuteur()).append("\n");
                sb.append("  • Date de retour: ").append(e.getDateRetourPrevue().format(formatter)).append("\n");
                if (i < emprunts.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        
        sb.append("\n───────────────────────────────────────────────\n");
        sb.append("Merci d'utiliser notre service de prêt libre-service!\n");
        
        // Mettre à jour le récapitulatif dans l'écran de reçu
        JPanel panelRecuCentre = (JPanel) panelRecu.getComponent(1);
        JScrollPane scrollRecap = (JScrollPane) panelRecuCentre.getComponent(0);
        JTextArea recapitulatif = (JTextArea) scrollRecap.getViewport().getView();
        recapitulatif.setText(sb.toString());
        
        // Afficher l'écran de reçu
        cardLayout.show(panelPrincipal, "RECU");
    }
    
    private void traiterChoixRecu(String choix, JTextArea recapitulatif) {
        String contenuRecu = recapitulatif.getText();
        
        switch (choix) {
            case "IMPRIME":
                try {
                    java.nio.file.Path out = java.nio.file.Paths.get("dist",
                        "recu_emprunt_" + System.currentTimeMillis() + ".txt");
                    java.nio.file.Files.createDirectories(out.getParent());
                    java.nio.file.Files.write(out, contenuRecu.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                    JOptionPane.showMessageDialog(this, 
                        "Reçu imprimé avec succès!\nFichier créé: " + out.toString(), 
                        "Succès", 
                        JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Impossible de créer le reçu: " + ex.getMessage(), 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "COURRIEL":
                JOptionPane.showMessageDialog(this, 
                    "Reçu envoyé par courriel (simulation).\n\n" + contenuRecu, 
                    "Reçu par courriel", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            case "PAS_RECU":
                JOptionPane.showMessageDialog(this, 
                    "Opération terminée sans reçu.", 
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
        // Retourner à l'écran de connexion
        champNumeroCompte.setText("");
        champNip.setText("");
        usagerConnecte = null;
        cardLayout.show(panelPrincipal, "CONNEXION");
    }
    
    private void afficherMessage(String message) {
        zoneAffichage.setText(message);
        labelStatut.setText("Opération réussie");
        labelStatut.setForeground(new Color(34, 139, 34));
    }
    
    private void mettreAJourStatut(String statut) {
        labelStatut.setText(statut);
        labelStatut.setForeground(new Color(50, 50, 50));
    }
}
