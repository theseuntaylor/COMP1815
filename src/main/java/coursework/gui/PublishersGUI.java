package coursework.gui;

import coursework.DDBB;
import coursework.model.Author;
import coursework.model.Book;
import coursework.model.Publisher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PublishersGUI {

    private static JFrame frame;

    private final DDBB databaseManager = DDBB.INSTANCE;
    public JPanel panel;
    private JButton savePublisherButton;
    private JTextField publisherNameTextField;

    private void addPublisher() {
        if (validFields()) {
            JOptionPane.showMessageDialog(null, "Please ensure all fields are correctly filled!");
        } else {
            Publisher newPublisher = new Publisher(
                    publisherNameTextField.getText()
            );
            databaseManager.addPublisher(newPublisher);
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private boolean validFields() {
        return publisherNameTextField.getText().isEmpty();
    }

    public PublishersGUI() {
        savePublisherButton.addActionListener(e -> addPublisher());
    }

    public static void main(String[] args) {
        frame = new JFrame("PublishersGUI");
        frame.setContentPane(new PublishersGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
