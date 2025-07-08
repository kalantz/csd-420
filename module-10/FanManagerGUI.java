/* Kypton Lantz
 * 07/08/2025
 * Advanced Java Programming – Module 10 Assignment
 * This program connects to databasedb using user student1 and password pass. It opens a Swing GUI with fields to enter an ID, view a fan record by ID, and update a fan record by ID with buttons to display and update.
 */
import javax.swing.*;
import java.awt.*;

public class FanManagerGUI extends JFrame {
    private JTextField idField, firstNameField, lastNameField, favoriteTeamField;
    private JButton displayButton, updateButton;

    public FanManagerGUI() {
        setTitle("Fan Manager");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        favoriteTeamField = new JTextField();

        add(new JLabel("Fan ID:")); add(idField);
        add(new JLabel("First Name:")); add(firstNameField);
        add(new JLabel("Last Name:")); add(lastNameField);
        add(new JLabel("Favorite Team:")); add(favoriteTeamField);

        displayButton = new JButton("Display");
        updateButton = new JButton("Update");

        add(displayButton); add(updateButton);

        displayButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Fan fan = FanSelector.getFanById(id);
                if (fan != null) {
                    firstNameField.setText(fan.getFirstname());
                    lastNameField.setText(fan.getLastname());
                    favoriteTeamField.setText(fan.getFavoriteteam());
                } else {
                    JOptionPane.showMessageDialog(this, "Fan not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String fname = firstNameField.getText().trim();
                String lname = lastNameField.getText().trim();
                String team = favoriteTeamField.getText().trim();

                Fan updatedFan = new Fan(id, fname, lname, team);
                boolean success = FanSelector.updateFan(updatedFan);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Fan updated.");
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed or fan not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            SwingUtilities.invokeLater(FanManagerGUI::new);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC Driver not found.");
        }
    }
}
