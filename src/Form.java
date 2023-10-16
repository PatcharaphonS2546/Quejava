import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
public class Form {
    public static void main(String[] args) {
        // Set the Nimbus look and feel with dark theme
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    NimbusLookAndFeel laf = (NimbusLookAndFeel) UIManager.getLookAndFeel();
                    laf.getDefaults().put("nimbusBase", new Color(18, 30, 49));
                    laf.getDefaults().put("nimbusBlueGrey", new Color(255, 255, 255));
                    laf.getDefaults().put("control", new Color(18, 30, 49));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the main frame
        JFrame frame = new JFrame("Que something");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); // Set the size to 800x600

        // Create a panel with a dark background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18, 30, 49));

        // Create a label with white text
        JLabel label = new JLabel("Enter your query:");
        label.setForeground(Color.WHITE);

        // Create an input text box
        JTextField inputTextField = new JTextField(20);

        // Create an output text box
        JTextArea outputTextArea = new JTextArea(30, 30);
        outputTextArea.setBackground(new Color(18, 30, 49));
        outputTextArea.setForeground(Color.WHITE);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);

        // Create a button to execute the query
        JButton queryButton = new JButton("Execute Query");

        // Add components to the panel
        panel.add(label);
        panel.add(inputTextField);
        panel.add(queryButton);
        panel.add(outputTextArea);

        // Add the panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);
    }
}

