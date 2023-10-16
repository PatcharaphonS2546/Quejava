import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "tester11448209";

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
        JFrame frame = new JFrame("Database Search GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size to 800x600

        // Create a panel with a dark background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18, 30, 49));

        // Create a label with white text for table name
        JLabel tableLabel = new JLabel("Enter Table Name:");
        tableLabel.setForeground(Color.WHITE);

        // Create an input text box for table name
        JTextField tableTextField = new JTextField(20);

        // Create a label with white text for column name
        JLabel columnLabel = new JLabel("Enter Column Name:");
        columnLabel.setForeground(Color.WHITE);

        // Create an input text box for column name
        JTextField columnTextField = new JTextField(20);

        // Create a search button
        JButton searchButton = new JButton("Search");

        // Create a JTable to display the results
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        // Customize the JTable appearance
        resultTable.setFillsViewportHeight(true);

        // Add components to the panel
        panel.add(tableLabel);
        panel.add(tableTextField);
        panel.add(columnLabel);
        panel.add(columnTextField);
        panel.add(searchButton);
        panel.add(scrollPane);

        // Add the panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tableName = tableTextField.getText();
                String columnName = columnTextField.getText();
                searchDatabase(tableName, columnName, tableModel);
            }
        });
    }

    private static void searchDatabase(String tableName, String columnName, DefaultTableModel tableModel) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT " + columnName + " FROM " + tableName;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Clear the existing table data
            tableModel.setRowCount(0);

            // Add column header to the table
            tableModel.setColumnIdentifiers(new Object[]{columnName});

            while (resultSet.next()) {
                String columnData = resultSet.getString(1); // Assuming you are selecting a single column
                tableModel.addRow(new Object[]{columnData});
            }

            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{"No results found."});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{"Error: " + e.getMessage()});
            e.printStackTrace();
        }
    }
}
