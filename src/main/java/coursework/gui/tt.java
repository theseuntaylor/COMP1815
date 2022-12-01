package coursework.gui;

import javax.swing.*;

public class tt {
    private JButton button1;
    private JPanel mainPanel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("tt");

        frame.setContentPane(new tt().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
