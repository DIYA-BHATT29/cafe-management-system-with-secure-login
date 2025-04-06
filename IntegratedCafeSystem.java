import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class IntegratedCafeSystem {
    public static void main(String[] args) {
        // Show the login page first
        showLoginPage();
    }

    private static void showLoginPage() {
        // Create frame
        JFrame frame = new JFrame("Login Page");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create panels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns

        // Create labels and text fields
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        
        // Create login button
        JButton loginButton = new JButton("Login");

        // Add components to panel
        panel.add(userLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        // Add panel to frame
        frame.add(panel);
     
        // Action for login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                // Simple validation
                if (username.equals("admin") && password.equals("password123")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose(); // Close login window
                    showCafeManagementSystem(); // Open main application
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                }
            }
        });
        
        // Set frame visible
        frame.setVisible(true);
    }

    private static void showCafeManagementSystem() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CafeManagementSystem();
        });
    }
}

class CafeManagementSystem extends JFrame implements ActionListener {
    // Color palette
    private final Color BACKGROUND_COLOR = new Color(255, 248, 220); // Light cream
    private final Color TITLE_COLOR = new Color(139, 69, 19); // Brown
    private final Color BUTTON_COLOR = new Color(210, 105, 30); // Chocolate
    private final Color BUTTON_HOVER = new Color(165, 42, 42); // Brown-red
    private final Color TEXT_COLOR = new Color(101, 67, 33); // Dark brown
    private final Color INPUT_BG = new Color(255, 253, 245); // Off-white
    private final Color OUTPUT_BG = new Color(253, 245, 230); // Lighter cream

    // UI Components
    private Container c;
    private JLabel title;
    private JPanel inputPanel, buttonPanel;
    private JTextField tItemName, tPrice, tQuantity;
    private JComboBox<String> categoryCombo;
    private JRadioButton available, notAvailable;
    private JTextArea tDescription, outputArea;
    private JButton addItem, updateItem, deleteItem, viewItems, clear;
    private JLabel status;

    // Data
    private List<MenuItem> menuItems;
    private String[] categories = {
        "☕ Coffee", "🍵 Tea", "🥤 Cold Drinks", 
        "🥪 Sandwiches", "🥐 Pastries", "🍳 Breakfast",
        "🍲 Lunch", "🍰 Desserts", "🌟 Specialty Items"
    };

    // MenuItem class
    private class MenuItem {
        String name;
        double price;
        String category;
        int quantity;
        boolean available;
        String description;
        
        public MenuItem(String name, double price, String category, 
                       int quantity, boolean available, String description) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.quantity = quantity;
            this.available = available;
            this.description = description;
        }
        
        @Override
        public String toString() {
            String avail = available ? "✅" : "❌";
            return String.format("%-20s $%-9.2f %-15s %-8d %-5s %s",
                name, price, category, quantity, avail, description);
        }
    }

    // Constructor
    public CafeManagementSystem() {
        menuItems = new ArrayList<>();
        
        setTitle("☕🍰 Cafe Management System");
        setBounds(300, 90, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOR);

        c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(BACKGROUND_COLOR);
        title = new JLabel("☕ Cafe Management System 🍰");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(TITLE_COLOR);
        titlePanel.add(title);
        c.add(titlePanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input Panel
        inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(TITLE_COLOR, 2), 
            "Menu Item Details",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            TITLE_COLOR));

        addFormField("🍴 Item Name", tItemName = createTextField());
        addFormField("💰 Price ($)", tPrice = createTextField());
        addFormField("📦 Category", categoryCombo = createComboBox());
        addFormField("🔢 Quantity", tQuantity = createTextField());
        addRadioButtons();
        addFormField("📝 Description", tDescription = createTextArea());

        mainPanel.add(inputPanel, BorderLayout.WEST);

        // Output Panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(BACKGROUND_COLOR);
        outputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(TITLE_COLOR, 2), 
            "Menu Items",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            TITLE_COLOR));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBackground(OUTPUT_BG);
        outputArea.setForeground(TEXT_COLOR);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane);

        mainPanel.add(outputPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        addItem = createButton("➕ Add Item");
        updateItem = createButton("✏️ Update Item");
        deleteItem = createButton("❌ Delete Item");
        viewItems = createButton("👀 View Items");
        clear = createButton("🧹 Clear");

        buttonPanel.add(addItem);
        buttonPanel.add(updateItem);
        buttonPanel.add(deleteItem);
        buttonPanel.add(viewItems);
        buttonPanel.add(clear);

        // Status Bar
        status = new JLabel("Ready...", JLabel.CENTER);
        status.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        status.setForeground(TITLE_COLOR);
        status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        c.add(mainPanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);
        c.add(status, BorderLayout.PAGE_END);

        setVisible(true);
    }

    // Helper methods for UI creation
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(INPUT_BG);
        field.setForeground(TEXT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(TITLE_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return field;
    }

    private JComboBox<String> createComboBox() {
        JComboBox<String> combo = new JComboBox<>(categories);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(INPUT_BG);
        combo.setForeground(TEXT_COLOR);
        return combo;
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea(3, 20);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setBackground(INPUT_BG);
        area.setForeground(TEXT_COLOR);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(TITLE_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return area;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        
        button.addActionListener(this);
        return button;
    }

    private void addFormField(String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TITLE_COLOR);
        inputPanel.add(label);
        inputPanel.add(field);
    }

    private void addRadioButtons() {
        JLabel label = new JLabel("📦 Availability");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TITLE_COLOR);
        inputPanel.add(label);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel.setBackground(BACKGROUND_COLOR);

        available = new JRadioButton("Available");
        available.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        available.setSelected(true);
        available.setBackground(BACKGROUND_COLOR);
        available.setForeground(TEXT_COLOR);

        notAvailable = new JRadioButton("Not Available");
        notAvailable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        notAvailable.setBackground(BACKGROUND_COLOR);
        notAvailable.setForeground(TEXT_COLOR);

        ButtonGroup group = new ButtonGroup();
        group.add(available);
        group.add(notAvailable);

        radioPanel.add(available);
        radioPanel.add(notAvailable);
        inputPanel.add(radioPanel);
    }

    // Action Listeners
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItem) {
            addItem();
        } else if (e.getSource() == updateItem) {
            updateItem();
        } else if (e.getSource() == deleteItem) {
            deleteItem();
        } else if (e.getSource() == viewItems) {
            viewItems();
        } else if (e.getSource() == clear) {
            clearFields();
        }
    }

    // Business Logic Methods
    private void addItem() {
        if (validateFields()) {
            String name = tItemName.getText().trim();
            
            for (MenuItem item : menuItems) {
                if (item.name.equalsIgnoreCase(name)) {
                    status.setText("⚠️ Item already exists! Use Update instead.");
                    return;
                }
            }
            
            MenuItem newItem = new MenuItem(
                name,
                Double.parseDouble(tPrice.getText()),
                (String)categoryCombo.getSelectedItem(),
                Integer.parseInt(tQuantity.getText()),
                available.isSelected(),
                tDescription.getText()
            );
            
            menuItems.add(newItem);
            status.setText("✅ Item added successfully!");
            clearFields();
        }
    }

    private void updateItem() {
        if (validateFields()) {
            String name = tItemName.getText().trim();
            boolean found = false;
            
            for (MenuItem item : menuItems) {
                if (item.name.equalsIgnoreCase(name)) {
                    item.price = Double.parseDouble(tPrice.getText());
                    item.category = (String)categoryCombo.getSelectedItem();
                    item.quantity = Integer.parseInt(tQuantity.getText());
                    item.available = available.isSelected();
                    item.description = tDescription.getText();
                    
                    found = true;
                    status.setText("✏️ Item updated successfully!");
                    clearFields();
                    break;
                }
            }
            
            if (!found) {
                status.setText("⚠️ Item not found. Use Add to create new item.");
            }
        }
    }

    private void deleteItem() {
        String name = tItemName.getText().trim();
        if (name.isEmpty()) {
            status.setText("⚠️ Please enter an item name to delete.");
            return;
        }

        boolean removed = menuItems.removeIf(item -> item.name.equalsIgnoreCase(name));
        
        if (removed) {
            status.setText("❌ Item deleted successfully!");
            clearFields();
        } else {
            status.setText("⚠️ Item not found.");
        }
    }

    private void viewItems() {
        if (menuItems.isEmpty()) {
            outputArea.setText("📭 No items in the menu yet.");
            status.setText("ℹ️ Menu is empty.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %-15s %-8s %-5s %s\n", 
            "Item", "Price", "Category", "Qty", "Avail", "Description"));
        sb.append("-------------------------------------------------------------\n");
        
        for (MenuItem item : menuItems) {
            sb.append(item.toString()).append("\n");
        }
        
        outputArea.setText(sb.toString());
        status.setText("👀 Displaying " + menuItems.size() + " items");
    }

    private boolean validateFields() {
        if (tItemName.getText().trim().isEmpty()) {
            status.setText("⚠️ Please enter an item name.");
            return false;
        }
        
        try {
            Double.parseDouble(tPrice.getText());
        } catch (NumberFormatException e) {
            status.setText("⚠️ Please enter a valid price.");
            return false;
        }
        
        try {
            Integer.parseInt(tQuantity.getText());
        } catch (NumberFormatException e) {
            status.setText("⚠️ Please enter a valid quantity.");
            return false;
        }
        
        return true;
    }

    private void clearFields() {
        tItemName.setText("");
        tPrice.setText("");
        categoryCombo.setSelectedIndex(0);
        tQuantity.setText("");
        available.setSelected(true);
        tDescription.setText("");
    }
}