import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AjoutParcelle extends JFrame {
    private Connection cn;
    private JButton boutonAjouter;
    private JPanel ParcelForm;
    private JLabel ParceTitre, AdresseLabel, SuperficieLabel, LatitudeLabel, LongitudeLabel, DescriptionLabel;
    private JTextField AdresseField, SuperficieField, LatitudeField, LongitudeField, descriptionField;

    public AjoutParcelle() {
        Connexion con = new Connexion();
        this.cn = con.laConnection();

        //=============================== Interface graphique ====================================//

        // Configuration de la fenêtre JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ajouter une parcelle");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        // Initialisation du JPanel
        ParcelForm = new JPanel();
        ParcelForm.setBounds(0, 0, 400, 600);
        ParcelForm.setLayout(null);
        getContentPane().add(ParcelForm);

        // Ajout des composants sur le JPanel
        //====titre====//
        ParceTitre = new JLabel("Informations sur la parcelle");
        ParceTitre.setBounds(10, 10, 380, 30);
        ParceTitre.setFont(new Font("Arial", Font.BOLD, 16));
        ParcelForm.add(ParceTitre);
        //====fin titre====//

        //====form===//
        AdresseLabel = new JLabel("Adresse:");
        AdresseLabel.setBounds(20, 70, 150, 30);
        AdresseLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(AdresseLabel);

        AdresseField = new JTextField();
        AdresseField.setBounds(20, 100, 350, 30);
        ParcelForm.add(AdresseField);

        SuperficieLabel = new JLabel("Superficie:");
        SuperficieLabel.setBounds(20, 145, 150, 30);
        SuperficieLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(SuperficieLabel);

        SuperficieField = new JTextField();
        SuperficieField.setBounds(20, 175, 350, 30);
        ParcelForm.add(SuperficieField);

        LatitudeLabel = new JLabel("Latitude:");
        LatitudeLabel.setBounds(20, 220, 150, 30);
        LatitudeLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(LatitudeLabel);

        LatitudeField = new JTextField();
        LatitudeField.setBounds(20, 250, 350, 30);
        ParcelForm.add(LatitudeField);

        LongitudeLabel = new JLabel("Longitude:");
        LongitudeLabel.setBounds(20, 295, 150, 30);
        LongitudeLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(LongitudeLabel);

        LongitudeField = new JTextField();
        LongitudeField.setBounds(20, 325, 350, 30);
        ParcelForm.add(LongitudeField);

        DescriptionLabel = new JLabel("Description de la région:");
        DescriptionLabel.setBounds(20, 370, 150, 30);
        DescriptionLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ParcelForm.add(DescriptionLabel);

        descriptionField = new JTextField();
        descriptionField.setBounds(20, 400, 350, 30);
        ParcelForm.add(descriptionField);

        boutonAjouter = new JButton("Suivant");
        boutonAjouter.setBounds(20, 450, 100, 30);
        boutonAjouter.setFont(new Font("Arial", Font.BOLD, 12));
        boutonAjouter.setBackground(new Color(50, 120, 50));
        boutonAjouter.setForeground(Color.WHITE);

        try {
            Image iconImage = ImageIO.read(getClass().getResource("/resources/fleche-droite.png"));
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

        AdresseField.getDocument().addDocumentListener(new DocumentListener() {
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

        SuperficieField.getDocument().addDocumentListener(new DocumentListener() {
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

        LatitudeField.getDocument().addDocumentListener(new DocumentListener() {
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

        LongitudeField.getDocument().addDocumentListener(new DocumentListener() {
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

        descriptionField.getDocument().addDocumentListener(new DocumentListener() {
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
            passerFormulaireProprietaire();
        });

        setVisible(true);
    }


    private boolean estFormulaireValide() {
        return !AdresseField.getText().isEmpty() && !SuperficieField.getText().isEmpty()
                && !LatitudeField.getText().isEmpty() && !LongitudeField.getText().isEmpty()
                && !descriptionField.getText().isEmpty();
    }

    private void passerFormulaireProprietaire() {
        // Masquer les composants du formulaire de la première étape
        ParceTitre.setVisible(false);
        AdresseLabel.setVisible(false);
        AdresseField.setVisible(false);
        SuperficieLabel.setVisible(false);
        SuperficieField.setVisible(false);
        LatitudeLabel.setVisible(false);
        LatitudeField.setVisible(false);
        LongitudeLabel.setVisible(false);
        LongitudeField.setVisible(false);
        DescriptionLabel.setVisible(false);
        descriptionField.setVisible(false);
        boutonAjouter.setVisible(false);

        // Récupérer les valeurs des champs
        String adresseParcelle = AdresseField.getText();
        String superficieParcelle = SuperficieField.getText();
        String latitudeParcelle = LatitudeField.getText();
        String longitudeParcelle = LongitudeField.getText();
        String descriptionParcelle = descriptionField.getText();

        // Créer une instance de la classe AjoutProprietaire et afficher son formulaire
        AjoutProprietaire ajoutProprietaire = new AjoutProprietaire(cn, ParcelForm, adresseParcelle, superficieParcelle, latitudeParcelle, longitudeParcelle, descriptionParcelle);
        ajoutProprietaire.afficherFormulaire();
    }


    public static void main(String[] args) {
        AjoutParcelle ajoutParcel = new AjoutParcelle();
    }
}
