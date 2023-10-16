import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class testconnection {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Database Table Display");
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        // Create a text field for the user to input the column name
        JTextField columnNameField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Create a panel to hold the input components
        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Enter Column Name: "));
        inputPanel.add(columnNameField);
        inputPanel.add(searchButton);

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // Define database connection details
        String DB_URL = "jdbc:mysql://localhost:3306/my_db";
        String DB_USER = "test";
        String DB_PASSWORD = "tester11448209";

        searchButton.addActionListener(e -> {
            String columnName = columnNameField.getText();
            model.setColumnCount(0); // Clear existing columns
            model.setRowCount(0);    // Clear existing rows

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement()) {
                // Construct and execute the SQL query
                String query = "SELECT * FROM book";
                ResultSet resultSet = statement.executeQuery(query);

                // Dynamically add the selected column to the table model
                model.addColumn(columnName);

                while (resultSet.next()) {
                    String columnData = resultSet.getString(columnName);
                    model.addRow(new Object[]{columnData});
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
