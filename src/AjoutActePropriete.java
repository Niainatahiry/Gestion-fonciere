import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AjoutActePropriete {
    private Connection cn;
    private JPanel ParcelForm;
    private int parcelId;
    private int ownerId;
    private JLabel ActeTitre, DateLabel, DroitLabel, TransactionsLabel, ChargeLabel;
    private JTextField DateField, DroitField, TransactionsField, ChargeField;
    private JButton boutonAjouter;

    public AjoutActePropriete(Connection cn, JPanel ParcelForm, int parcelId, int ownerId) {
        this.cn = cn;
        this.ParcelForm = ParcelForm;
        this.parcelId = parcelId;
        this.ownerId = ownerId;
    }

    public void afficherFormulaire() {
        //====titre====//
        ActeTitre = new JLabel("Informations sur l'acte de propriété");
        ActeTitre.setBounds(10, 10, 380, 30);
        ActeTitre.setFont(new Font("Arial", Font.BOLD, 16));
        ParcelForm.add(ActeTitre);
        //====fin titre====//

        //====form===//
        DateLabel = new JLabel("Date d'acquisition:");
        DateLabel.setBounds(20, 70, 150, 30);
        DateLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(DateLabel);

        DateField = new JTextField();
        DateField.setBounds(20, 100, 350, 30);
        ParcelForm.add(DateField);

        DroitLabel = new JLabel("Droit d'utilisation:");
        DroitLabel.setBounds(20, 145, 150, 30);
        DroitLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(DroitLabel);

        DroitField = new JTextField();
        DroitField.setBounds(20, 175, 350, 30);
        ParcelForm.add(DroitField);

        TransactionsLabel = new JLabel("Historique des transactions:");
        TransactionsLabel.setBounds(20, 220, 180, 30);
        TransactionsLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(TransactionsLabel);

        TransactionsField = new JTextField();
        TransactionsField.setBounds(20, 250, 350, 30);
        ParcelForm.add(TransactionsField);

        ChargeLabel = new JLabel("Charge:");
        ChargeLabel.setBounds(20, 295, 150, 30);
        ChargeLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(ChargeLabel);

        ChargeField = new JTextField();
        ChargeField.setBounds(20, 325, 350, 30);
        ParcelForm.add(ChargeField);

        boutonAjouter = new JButton("Ajouter");
        boutonAjouter.setBounds(20, 375, 100, 30);
        boutonAjouter.setFont(new Font("Arial", Font.BOLD, 12));
        boutonAjouter.setBackground(new Color(50, 120, 50));
        boutonAjouter.setForeground(Color.WHITE);

        try {
            Image iconImage = ImageIO.read(getClass().getResource("/resources/verifier.png"));
            int nouvelleLargeur = 20;
            int nouvelleHauteur = 20;
            Image imageRedimensionnee = iconImage.getScaledInstance(nouvelleLargeur, nouvelleHauteur, Image.SCALE_SMOOTH);
            ImageIcon iconRedimensionne = new ImageIcon(imageRedimensionnee);
            boutonAjouter.setHorizontalTextPosition(SwingConstants.LEFT);
            boutonAjouter.setIcon(iconRedimensionne);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ParcelForm.add(boutonAjouter);
        boutonAjouter.setEnabled(false); // Désactiver le bouton au départ

        DateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }
        });

        DroitField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }
        });

        TransactionsField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }
        });

        ChargeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boutonAjouter.setEnabled(estFormulaireValide());
            }

        });

        boutonAjouter.addActionListener(e -> {
            int result = insererDonneesActePropriete();
            if (result > 0) {
                JOptionPane.showMessageDialog(ParcelForm, "Données insérées avec succès.");
                // Ajouter ici les actions à effectuer après l'ajout de l'acte de propriété
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ParcelForm);
                frame.dispose(); // Fermer la fenêtre après l'ajout
            } else {
                JOptionPane.showMessageDialog(ParcelForm, "Erreur lors de l'insertion des données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ajout d'un gestionnaire d'événements pour empêcher la fermeture de l'application si le formulaire n'est pas complètement rempli
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ParcelForm);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (!estFormulaireValide()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs du formulaire avant de quitter.",
                            "Formulaire incomplet", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
    }

    private int insererDonneesActePropriete() {
        String dateAcquisition = DateField.getText();
        String droitUtilisation = DroitField.getText();
        String historiqueTransactions = TransactionsField.getText();
        String charge = ChargeField.getText();

        int rowsAffected = 0;
        try {
            // Insérer les données de l'acte de propriété
            String query = "INSERT INTO propertytitle (parcel_id, owner_id, date_acquisition, droits_utilisation, historique_transactions, charges) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = cn.prepareStatement(query);
            statement.setInt(1, parcelId);
            statement.setInt(2, ownerId);
            statement.setString(3, dateAcquisition);
            statement.setString(4, droitUtilisation);
            statement.setString(5, historiqueTransactions);
            statement.setString(6, charge);

            rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Données de l'acte de propriété insérées avec succès !");
            } else {
                System.out.println("Erreur lors de l'insertion des données de l'acte de propriété.");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    private boolean estFormulaireValide() {
        return !DateField.getText().isEmpty() && !DroitField.getText().isEmpty()
                && !TransactionsField.getText().isEmpty() && !ChargeField.getText().isEmpty();
    }
}
