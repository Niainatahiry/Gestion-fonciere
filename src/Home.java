import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Home extends JFrame {
    private JButton ajoutParcelleBtn;
    private JPanel homePanel;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JXTable table;

    private static Connection cn;

    public Home() throws IOException {
        setTitle("Page d'accueil");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 700));

        // Background image
        ImageIcon background = new ImageIcon(getClass().getResource("/resources/bg_img.png"));
        Image image = background.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledBackground = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(scaledBackground);
        backgroundLabel.setBounds(0, 0, 900, 700);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Home panel with components
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setOpaque(false);
        homePanel.setBounds(0, 0, 900, 700);
        layeredPane.add(homePanel, JLayeredPane.PALETTE_LAYER);

        ajoutParcelleBtn = new JButton("Ajouter une parcelle");
        ajoutParcelleBtn.setBounds(480, 20, 340, 25);
        ajoutParcelleBtn.setFont(new Font("Arial", Font.BOLD, 12));
        ajoutParcelleBtn.setBackground(new Color(50, 120, 50));
        ajoutParcelleBtn.setForeground(Color.WHITE);
        ajoutParcelleBtn.setFocusPainted(false);
        ImageIcon plusIcon = new ImageIcon(getClass().getResource("/resources/plus.png"));
        Image scaledIcon = plusIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ajoutParcelleBtn.setIcon(new ImageIcon(scaledIcon));
        ajoutParcelleBtn.setHorizontalTextPosition(SwingConstants.RIGHT);

        ajoutParcelleBtn.addActionListener(e -> {
            AjoutParcelle ajoutParcelle = new AjoutParcelle();
            ajoutParcelle.setVisible(true);
        });

        homePanel.add(ajoutParcelleBtn);

        searchField = new JTextField();
        searchField.setBounds(480, 70, 200, 25);
        homePanel.add(searchField);

        searchButton = new JButton("Rechercher");
        searchButton.setBounds(690, 70, 130, 25);
        searchButton.setBackground(new Color(50, 120, 50));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        // Ajouter une icône de recherche au bouton "Rechercher"
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/resources/chercher.png"));
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(scaledSearchIcon));
        searchButton.setHorizontalTextPosition(SwingConstants.RIGHT);

        searchButton.addActionListener(e -> {
            String searchQuery = searchField.getText();
            performSearch(searchQuery);
        });
        homePanel.add(searchButton);

        table = new JXTable();
        table.setRowHeight(30);
        table.setFont(table.getFont().deriveFont(Font.BOLD, 11));
        table.setDragEnabled(true); // Activation du défilement horizontal
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 100, 830, 470);
        homePanel.add(scrollPane);

        setContentPane(layeredPane);
        setVisible(true);
    }

    public void setConnection(Connection connection) {
        this.cn = connection;
    }

    private void performSearch(String searchQuery) {
        try {
            String query = "SELECT * FROM owner WHERE Nom LIKE ? OR Prenom LIKE ?";
            PreparedStatement statement = cn.prepareStatement(query);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setString(2, "%" + searchQuery + "%");
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id");
            model.addColumn("Nom");
            model.addColumn("Prénom");
            model.addColumn("Adresse");
            model.addColumn("Email");
            model.addColumn("Téléphone");
            model.addColumn(""); // Colonne vide pour le bouton "Afficher plus"

            while (resultSet.next()) {
                int id = resultSet.getInt("owner_id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String email = resultSet.getString("email");
                String telephone = resultSet.getString("telephone");

                Object[] rowData = new Object[]{id, nom, prenom, adresse, email, telephone, "Afficher plus"};
                model.addRow(rowData);
            }

            table.setModel(model);

            // Rendu personnalisé pour la dernière colonne (bouton "Afficher plus")
            table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(table));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                Connexion connexion = new Connexion();
                Connection cn = connexion.laConnection();

                Home home = new Home();
                home.setConnection(cn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
