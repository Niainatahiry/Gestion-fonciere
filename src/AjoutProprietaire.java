import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AjoutProprietaire {
    private final String descriptionParcelle;
    private String adresseParcelle;
    private double superficieParcelle;
    private double latitudeParcelle;
    private double longitudeParcelle;

    private Connection cn;
    private JPanel ParcelForm;
    private JLabel ProprietaireTitre, NomLabel, PrenomLabel, AdresseLabel, EmailLabel, TelephoneLabel;
    private JTextField NomField, PrenomField, AdresseField, EmailField, TelephoneField;
    private JButton boutonSuivant, boutonDejaProprietaire;

    public AjoutProprietaire(Connection cn, JPanel ParcelForm, String adresseParcelle, String superficieParcelle, String latitudeParcelle, String longitudeParcelle, String descriptionParcelle) {
        this.cn = cn;
        this.ParcelForm = ParcelForm;
        this.adresseParcelle = adresseParcelle;
        this.superficieParcelle = Double.parseDouble(superficieParcelle);
        this.latitudeParcelle = Double.parseDouble(latitudeParcelle);
        this.longitudeParcelle = Double.parseDouble(longitudeParcelle);
        this.descriptionParcelle = descriptionParcelle;
    }

    public void afficherFormulaire() {
        //====titre====//
        ProprietaireTitre = new JLabel("Informations sur le propriétaire");
        ProprietaireTitre.setBounds(10, 10, 380, 30);
        ProprietaireTitre.setFont(new Font("Arial", Font.BOLD, 16));
        ParcelForm.add(ProprietaireTitre);
        //====fin titre====//

        //====form===//
        NomLabel = new JLabel("Nom:");
        NomLabel.setBounds(20, 70, 150, 30);
        NomLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(NomLabel);

        NomField = new JTextField();
        NomField.setBounds(20, 100, 350, 30);
        ParcelForm.add(NomField);

        PrenomLabel = new JLabel("Prénom:");
        PrenomLabel.setBounds(20, 145, 150, 30);
        PrenomLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(PrenomLabel);

        PrenomField = new JTextField();
        PrenomField.setBounds(20, 175, 350, 30);
        ParcelForm.add(PrenomField);

        AdresseLabel = new JLabel("Adresse:");
        AdresseLabel.setBounds(20, 220, 150, 30);
        AdresseLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(AdresseLabel);

        AdresseField = new JTextField();
        AdresseField.setBounds(20, 250, 350, 30);
        ParcelForm.add(AdresseField);

        EmailLabel = new JLabel("Email:");
        EmailLabel.setBounds(20, 295, 150, 30);
        EmailLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(EmailLabel);

        EmailField = new JTextField();
        EmailField.setBounds(20, 325, 350, 30);
        ParcelForm.add(EmailField);

        TelephoneLabel = new JLabel("Téléphone:");
        TelephoneLabel.setBounds(20, 370, 150, 30);
        TelephoneLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(TelephoneLabel);

        TelephoneField = new JTextField();
        TelephoneField.setBounds(20, 400, 350, 30);
        ParcelForm.add(TelephoneField);

        boutonSuivant = new JButton("Ajouter");
        boutonSuivant.setBounds(20, 450, 100, 30);
        boutonSuivant.setFont(new Font("Arial", Font.BOLD, 12));
        boutonSuivant.setBackground(new Color(50, 120, 50));
        boutonSuivant.setForeground(Color.WHITE);

        boutonDejaProprietaire = new JButton("Déjà propriétaire");
        boutonDejaProprietaire.setBounds(140, 450, 140, 30);
        boutonDejaProprietaire.setFont(new Font("Arial", Font.BOLD, 12));
        boutonDejaProprietaire.setBackground(new Color(150, 150, 50));
        boutonDejaProprietaire.setForeground(Color.WHITE);
        ParcelForm.add(boutonDejaProprietaire);

        try {
            Image iconImage = ImageIO.read(getClass().getResource("/resources/verifier.png"));
            int nouvelleLargeur = 20;
            int nouvelleHauteur = 20;
            Image imageRedimensionnee = iconImage.getScaledInstance(nouvelleLargeur, nouvelleHauteur, Image.SCALE_SMOOTH);
            ImageIcon iconRedimensionne = new ImageIcon(imageRedimensionnee);
            boutonSuivant.setHorizontalTextPosition(SwingConstants.LEFT);
            boutonSuivant.setIcon(iconRedimensionne);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ParcelForm.add(boutonSuivant);
        boutonSuivant.setEnabled(false); // Désactiver le bouton au départ

        NomField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }
        });

        PrenomField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }
        });

        AdresseField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }
        });

        EmailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }
        });

        TelephoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonSuivant.setEnabled(estFormulaireValide());
            }
        });

        boutonSuivant.addActionListener(e -> {
            insererDonneesProprietaire();
            // Ajouter ici les actions à effectuer après l'ajout du propriétaire
            int parcelId = getLastInsertedParcelId();
            int ownerId = getLastInsertedOwnerId();
            if (parcelId != -1 && ownerId != -1) {
                ProprietaireTitre.setVisible(false);
                AdresseLabel.setVisible(false);
                AdresseField.setVisible(false);
                NomLabel.setVisible(false);
                NomField.setVisible(false);
                PrenomLabel.setVisible(false);
                PrenomField.setVisible(false);
                EmailLabel.setVisible(false);
                EmailField.setVisible(false);
                TelephoneLabel.setVisible(false);
                TelephoneField.setVisible(false);
                boutonSuivant.setVisible(false);
                AjoutActePropriete ajoutActePropriete = new AjoutActePropriete(cn, ParcelForm, parcelId, ownerId);
                ajoutActePropriete.afficherFormulaire();
            } else {
                JOptionPane.showMessageDialog(ParcelForm, "Une erreur s'est produite lors de la récupération des ID de parcelle et de propriétaire.",
                        "Erreur d'ID", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void insererDonneesProprietaire() {
        String nom = NomField.getText();
        String prenom = PrenomField.getText();
        String adresse = AdresseField.getText();
        String email = EmailField.getText();
        String telephone = TelephoneField.getText();

        try {
            // Insérer les données de la parcelle
            String queryParcelle = "INSERT INTO Parcel (adresse, superficie, coordonnees_latitude, coordonnees_longitude, description_region) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statementParcelle = cn.prepareStatement(queryParcelle);
            statementParcelle.setString(1, adresseParcelle);
            statementParcelle.setDouble(2, superficieParcelle);
            statementParcelle.setDouble(3, latitudeParcelle);
            statementParcelle.setDouble(4, longitudeParcelle);
            statementParcelle.setString(5, descriptionParcelle);

            int rowsAffectedParcelle = statementParcelle.executeUpdate();

            if (rowsAffectedParcelle > 0) {
                System.out.println("Données de la parcelle insérées avec succès !");
            } else {
                System.out.println("Erreur lors de l'insertion des données de la parcelle.");
            }

            statementParcelle.close();

            // Insérer les données du propriétaire
            String queryProprietaire = "INSERT INTO owner (nom, prenom, adresse, email, telephone) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statementProprietaire = cn.prepareStatement(queryProprietaire);
            statementProprietaire.setString(1, nom);
            statementProprietaire.setString(2, prenom);
            statementProprietaire.setString(3, adresse);
            statementProprietaire.setString(4, email);
            statementProprietaire.setString(5, telephone);

            int rowsAffectedProprietaire = statementProprietaire.executeUpdate();

            if (rowsAffectedProprietaire > 0) {
                System.out.println("Données du propriétaire insérées avec succès !");
            } else {
                System.out.println("Erreur lors de l'insertion des données du propriétaire.");
            }

            statementProprietaire.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean estFormulaireValide() {
        return !NomField.getText().isEmpty() && !PrenomField.getText().isEmpty()
                && !AdresseField.getText().isEmpty() && !EmailField.getText().isEmpty()
                && !TelephoneField.getText().isEmpty();
    }

    private int getLastInsertedParcelId() {
        try {
            String query = "SELECT LAST_INSERT_ID()";
            PreparedStatement statement = cn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int getLastInsertedOwnerId() {
        try {
            String query = "SELECT LAST_INSERT_ID()";
            PreparedStatement statement = cn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}

