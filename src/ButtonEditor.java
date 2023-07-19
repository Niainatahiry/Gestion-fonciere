import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JTable table) {
        this.table = table;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données de la ligne sélectionnée
                int selectedRow = table.getSelectedRow();
                String nom = table.getValueAt(selectedRow, 1).toString();
                String prenom = table.getValueAt(selectedRow, 2).toString();
                String adresse = table.getValueAt(selectedRow, 3).toString();
                String email = table.getValueAt(selectedRow, 4).toString();
                String telephone = table.getValueAt(selectedRow, 5).toString();

                // Afficher une boîte de dialogue avec les informations
                String message = "Nom : " + nom + "\nPrénom : " + prenom + "\nAdresse : " + adresse +
                        "\nEmail : " + email + "\nTéléphone : " + telephone;
                JOptionPane.showMessageDialog(button, message);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }

        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Action personnalisée du bouton
            // Vous pouvez ajouter votre propre logique ici
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
