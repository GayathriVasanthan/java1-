import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ExpenseTracker extends JFrame {
    private JTextField descriptionField = new JTextField(10);
    private JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Food", "Transport", "Bills", "Entertainment", "Other"});
    private JTextField amountField = new JTextField(7);

    private DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Description", "Category", "Amount"}, 0);
    private JTable expenseTable = new JTable(tableModel);

    private JLabel totalLabel = new JLabel("Total Expense: 0.00");

    public ExpenseTracker() {
        super("Personal Expense Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryBox);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        JButton addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by Category:"));
        JComboBox<String> filterBox = new JComboBox<>(new String[]{"All", "Food", "Transport", "Bills", "Entertainment", "Other"});
        filterPanel.add(filterBox);

        JScrollPane tableScrollPane = new JScrollPane(expenseTable);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(totalLabel);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(filterPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);
        add(totalPanel, BorderLayout.PAGE_END);

        addButton.addActionListener(e -> addExpense());
        filterBox.addActionListener(e -> filterExpenses((String) filterBox.getSelectedItem()));
        updateTotal();
    }

    private void addExpense() {
        String desc = descriptionField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        String amountText = amountField.getText().trim();

        if (desc.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter description and amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount < 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive.");
                return;
            }
            tableModel.addRow(new Object[]{desc, category, amount});
            descriptionField.setText("");
            amountField.setText("");
            categoryBox.setSelectedIndex(0);
            updateTotal();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    private void filterExpenses(String category) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        expenseTable.setRowSorter(sorter);
        if ("All".equals(category)) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(category, 1));
        }
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (double) tableModel.getValueAt(i, 2);
        }
        totalLabel.setText(String.format("Total Expense: %.2f", total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker().setVisible(true));
    }
}
