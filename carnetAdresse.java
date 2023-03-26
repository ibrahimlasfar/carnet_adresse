import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class carnetAdresse extends JFrame {

    // Définir les composants de l'interface graphique
    private JLabel nomLabel = new JLabel("Nom : ");
    private JTextField nomTextField = new JTextField(20);
    private JLabel prenomLabel = new JLabel("Prénom : ");
    private JTextField prenomTextField = new JTextField(20);
    private JLabel telLabel = new JLabel("Téléphone : ");
    private JTextField telTextField = new JTextField(20);
    private JLabel adresseLabel = new JLabel("Adresse : ");
    private JTextField adresseTextField = new JTextField(20);
    private JLabel cpLabel = new JLabel("Code postal : ");
    private JTextField cpTextField = new JTextField(20);
    private JLabel villeLabel = new JLabel("Ville : ");
    private JTextField villeTextField = new JTextField(20);
    private JButton ajouterButton = new JButton("Ajouter");
    private JButton supprimerButton = new JButton("Supprimer");
    private JButton modifierButton = new JButton("Modifier");
    private JTable carnetTable;
    private DefaultTableModel carnetModel = new DefaultTableModel();

    public carnetAdresse() {
        // Définir les propriétés de la fenêtre
        setTitle("Carnet d'adresses");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        // Créer les conteneurs pour le panneau et les boutons
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Ajouter les composants au panneau du formulaire
        formPanel.add(nomLabel);
        formPanel.add(nomTextField);
        formPanel.add(prenomLabel);
        formPanel.add(prenomTextField);
        formPanel.add(telLabel);
        formPanel.add(telTextField);
        formPanel.add(adresseLabel);
        formPanel.add(adresseTextField);
        formPanel.add(cpLabel);
        formPanel.add(cpTextField);
        formPanel.add(villeLabel);
        formPanel.add(villeTextField);

        // Ajouter les boutons au panneau des boutons
        buttonPanel.add(ajouterButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(modifierButton);

        // Ajouter les panneaux à la fenêtre principale
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ajouter la table à la fenêtre principale
        carnetTable = new JTable(carnetModel);
        carnetModel.addColumn("Nom");
        carnetModel.addColumn("Prénom");
        carnetModel.addColumn("Téléphone");
        carnetModel.addColumn("Adresse");
        carnetModel.addColumn("Code postal");
        carnetModel.addColumn("Ville");
        JScrollPane scrollPane = new JScrollPane(carnetTable);
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter les écouteurs d'événements
        ajouterButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les informations saisies dans les champs de texte
                String nom = nomTextField.getText();
                String prenom = prenomTextField.getText();
                String tel = telTextField.getText();
                String adresse = adresseTextField.getText();
                String cp = cpTextField.getText();
                String ville = villeTextField.getText();
        
                // Ajouter une nouvelle entrée dans le carnet d'adresses
                carnetModel.addRow(new Object[]{nom, prenom, tel, adresse, cp, ville});
        
                // Effacer les champs de texte après l'ajout
                nomTextField.setText("");
                prenomTextField.setText("");
                telTextField.setText("");
                adresseTextField.setText("");
                cpTextField.setText("");
                villeTextField.setText("");
        }
        
      
    });
    // Ecouteur pour le bouton "Modifier"
modifierButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Récupérer la ligne sélectionnée dans la table
        int row = carnetTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(carnetAdresse.this, "Veuillez sélectionner une ligne à modifier.");
            return;
        }

        // Récupérer la colonne sélectionnée dans la table
        int column = carnetTable.getSelectedColumn();

        // Vérifier que la colonne sélectionnée est modifiable
        if (column == 0 || column == 1) {
            JOptionPane.showMessageDialog(carnetAdresse.this, "Vous ne pouvez pas modifier ce champ.");
            return;
        }

        // Récupérer la valeur du champ à modifier
        String value = JOptionPane.showInputDialog(carnetAdresse.this, "Veuillez saisir la nouvelle valeur :");

        // Mettre à jour la valeur du champ dans la table
        carnetModel.setValueAt(value, row, column);
    }
});


// Ecouteur pour le bouton "Supprimer"
supprimerButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Récupérer la ligne sélectionnée dans la table
        int selectedRow = carnetTable.getSelectedRow();
        
        // Vérifier si une ligne a bien été sélectionnée
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(carnetAdresse.this, "Veuillez sélectionner une ligne à supprimer.");
        } else {
            // Supprimer la ligne sélectionnée de la table
            carnetModel.removeRow(selectedRow);
            
            // Vider les champs de texte après la suppression
            nomTextField.setText("");
            prenomTextField.setText("");
            telTextField.setText("");
            adresseTextField.setText("");
            cpTextField.setText("");
            villeTextField.setText("");
        }
    }
});

}

            
    public static void main(String[] args) {
        carnetAdresse carnet = new carnetAdresse();
        carnet.setVisible(true);
    }
}
