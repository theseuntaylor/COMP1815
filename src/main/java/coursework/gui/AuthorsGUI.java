package coursework.gui;

import coursework.DDBB;
import coursework.model.Author;
import coursework.model.Publisher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorsGUI {
    private static JFrame frame;
    private final DDBB databaseManager = DDBB.INSTANCE;
    private JPanel panel1;
    private JButton saveAuthorButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;


    private void addAuthor() {
        if (validFields()) {
            JOptionPane.showMessageDialog(null, "Please ensure all fields are correctly filled!");
        } else {
            Author newAuthor = new Author(
                    firstNameTextField.getText(),
                    lastNameTextField.getText()
            );
            databaseManager.addAuthor(newAuthor.toString());
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private boolean validFields() {
        return firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty();
    }

    public AuthorsGUI() {
        saveAuthorButton.addActionListener(e -> addAuthor());
    }

    public static void main(String[] args) {
        frame = new JFrame("AuthorsGUI");
        frame.setContentPane(new AuthorsGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
