import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class CafeManagementSystem extends JFrame implements ActionListener {
   
    private Container c;
    private JLabel title;
    private JLabel itemName;
    private JTextField tItemName;
    private JLabel price;
    private JTextField tPrice;
    private JLabel category;
    private JComboBox<String> categoryCombo;
    private JLabel quantity;
    private JTextField tQuantity;
    private JLabel availability;
    private JRadioButton available;
    private JRadioButton notAvailable;
    private ButtonGroup availabilityGroup;
    private JLabel description;
    private JTextArea tDescription;
    private JButton addItem;
    private JButton updateItem;
    private JButton deleteItem;
    private JButton viewItems;
    private JButton clear;
    private JTextArea outputArea;
    private JLabel status;
    
    
    private List<MenuItem> menuItems;
    
    
    private String[] categories = {
        "Coffee", "Tea", "Cold Drinks", 
        "Sandwiches", "Pastries", "Breakfast",
        "Lunch", "Desserts", "Specialty Items"
    };

    
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
            return String.format("%-20s $%-9.2f %-15s %-8d %-10s %s",
                name, price, category, quantity, 
                available ? "Yes" : "No", description);
        }
    }

    
    public CafeManagementSystem() {
        
        menuItems = new ArrayList<>();
        
        setTitle("Cafe Management System");
        setBounds(300, 90, 900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Cafe Management System");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 30);
        title.setLocation(250, 30);
        c.add(title);

        itemName = new JLabel("Item Name");
        itemName.setFont(new Font("Arial", Font.PLAIN, 18));
        itemName.setSize(150, 20);
        itemName.setLocation(100, 100);
        c.add(itemName);

        tItemName = new JTextField();
        tItemName.setFont(new Font("Arial", Font.PLAIN, 15));
        tItemName.setSize(200, 25);
        tItemName.setLocation(250, 100);
        c.add(tItemName);

        price = new JLabel("Price ($)");
        price.setFont(new Font("Arial", Font.PLAIN, 18));
        price.setSize(150, 20);
        price.setLocation(100, 150);
        c.add(price);

        tPrice = new JTextField();
        tPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        tPrice.setSize(100, 25);
        tPrice.setLocation(250, 150);
        c.add(tPrice);

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.PLAIN, 18));
        category.setSize(150, 20);
        category.setLocation(100, 200);
        c.add(category);

        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setFont(new Font("Arial", Font.PLAIN, 15));
        categoryCombo.setSize(150, 25);
        categoryCombo.setLocation(250, 200);
        c.add(categoryCombo);

        quantity = new JLabel("Quantity");
        quantity.setFont(new Font("Arial", Font.PLAIN, 18));
        quantity.setSize(150, 20);
        quantity.setLocation(100, 250);
        c.add(quantity);

        tQuantity = new JTextField();
        tQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        tQuantity.setSize(50, 25);
        tQuantity.setLocation(250, 250);
        c.add(tQuantity);

        availability = new JLabel("Availability");
        availability.setFont(new Font("Arial", Font.PLAIN, 18));
        availability.setSize(150, 20);
        availability.setLocation(100, 300);
        c.add(availability);

        available = new JRadioButton("Available");
        available.setFont(new Font("Arial", Font.PLAIN, 15));
        available.setSelected(true);
        available.setSize(100, 20);
        available.setLocation(250, 300);
        c.add(available);

        notAvailable = new JRadioButton("Not Available");
        notAvailable.setFont(new Font("Arial", Font.PLAIN, 15));
        notAvailable.setSelected(false);
        notAvailable.setSize(120, 20);
        notAvailable.setLocation(350, 300);
        c.add(notAvailable);

        availabilityGroup = new ButtonGroup();
        availabilityGroup.add(available);
        availabilityGroup.add(notAvailable);

        description = new JLabel("Description");
        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setSize(150, 20);
        description.setLocation(100, 350);
        c.add(description);

        tDescription = new JTextArea();
        tDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        tDescription.setSize(300, 75);
        tDescription.setLocation(250, 350);
        tDescription.setLineWrap(true);
        c.add(tDescription);

        addItem = new JButton("Add Item");
        addItem.setFont(new Font("Arial", Font.PLAIN, 15));
        addItem.setSize(120, 30);
        addItem.setLocation(100, 450);
        addItem.addActionListener(this);
        c.add(addItem);

        updateItem = new JButton("Update Item");
        updateItem.setFont(new Font("Arial", Font.PLAIN, 15));
        updateItem.setSize(120, 30);
        updateItem.setLocation(230, 450);
        updateItem.addActionListener(this);
        c.add(updateItem);

        deleteItem = new JButton("Delete Item");
        deleteItem.setFont(new Font("Arial", Font.PLAIN, 15));
        deleteItem.setSize(120, 30);
        deleteItem.setLocation(360, 450);
        deleteItem.addActionListener(this);
        c.add(deleteItem);

        viewItems = new JButton("View Items");
        viewItems.setFont(new Font("Arial", Font.PLAIN, 15));
        viewItems.setSize(120, 30);
        viewItems.setLocation(490, 450);
        viewItems.addActionListener(this);
        c.add(viewItems);

        clear = new JButton("Clear");
        clear.setFont(new Font("Arial", Font.PLAIN, 15));
        clear.setSize(120, 30);
        clear.setLocation(620, 450);
        clear.addActionListener(this);
        c.add(clear);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setSize(400, 400);
        outputArea.setLocation(450, 100);
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(450, 100, 400, 300);
        c.add(scrollPane);

        status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 16));
        status.setSize(500, 25);
        status.setLocation(100, 500);
        c.add(status);

        setVisible(true);
    }

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

    private void addItem() {
        if (validateFields()) {
            String name = tItemName.getText().trim();
            
            // Check if item already exists
            for (MenuItem item : menuItems) {
                if (item.name.equalsIgnoreCase(name)) {
                    status.setText("Item already exists! Use Update instead.");
                    return;
                }
            }
            
            // Create new menu item
            MenuItem newItem = new MenuItem(
                name,
                Double.parseDouble(tPrice.getText()),
                (String)categoryCombo.getSelectedItem(),
                Integer.parseInt(tQuantity.getText()),
                available.isSelected(),
                tDescription.getText()
            );
            
            menuItems.add(newItem);
            status.setText("Item added successfully!");
            clearFields();
        }
    }

    private void updateItem() {
        if (validateFields()) {
            String name = tItemName.getText().trim();
            boolean found = false;
            
            for (MenuItem item : menuItems) {
                if (item.name.equalsIgnoreCase(name)) {
                    // Update the existing item
                    item.price = Double.parseDouble(tPrice.getText());
                    item.category = (String)categoryCombo.getSelectedItem();
                    item.quantity = Integer.parseInt(tQuantity.getText());
                    item.available = available.isSelected();
                    item.description = tDescription.getText();
                    
                    found = true;
                    status.setText("Item updated successfully!");
                    clearFields();
                    break;
                }
            }
            
            if (!found) {
                status.setText("Item not found. Use Add to create new item.");
            }
        }
    }

    private void deleteItem() {
        String name = tItemName.getText().trim();
        if (name.isEmpty()) {
            status.setText("Please enter an item name to delete.");
            return;
        }

        boolean removed = menuItems.removeIf(item -> item.name.equalsIgnoreCase(name));
        
        if (removed) {
            status.setText("Item deleted successfully!");
            clearFields();
        } else {
            status.setText("Item not found.");
        }
    }

    private void viewItems() {
        if (menuItems.isEmpty()) {
            outputArea.setText("No items in the menu yet.");
            status.setText("Menu is empty.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %-15s %-8s %-10s %s\n", 
            "Item Name", "Price", "Category", "Qty", "Available", "Description"));
        sb.append("----------------------------------------------------------------------------\n");
        
        for (MenuItem item : menuItems) {
            sb.append(item.toString()).append("\n");
        }
        
        outputArea.setText(sb.toString());
        status.setText(menuItems.size() + " items displayed.");
    }

    private boolean validateFields() {
        if (tItemName.getText().trim().isEmpty()) {
            status.setText("Please enter an item name.");
            return false;
        }
        
        try {
            Double.parseDouble(tPrice.getText());
        } catch (NumberFormatException e) {
            status.setText("Please enter a valid price.");
            return false;
        }
        
        try {
            Integer.parseInt(tQuantity.getText());
        } catch (NumberFormatException e) {
            status.setText("Please enter a valid quantity.");
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

    public static void main(String[] args) throws Exception {
        CafeManagementSystem system = new CafeManagementSystem();
    }
}
