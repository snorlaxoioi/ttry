import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoffeeOrderingApp {

    static class Coffee {
        String name;
        String price;
        ImageIcon image;

        public Coffee(String name, String price, ImageIcon image) {
            this.name = name;
            this.price = price;
            this.image = image;
        }
    }

    private JFrame frame;
    private JPanel menuPanel;
    private JPanel orderPanel;
    private DefaultListModel<String> orderListModel;
    private JLabel totalLabel;
    private ArrayList<Coffee> coffeeMenu;
    private double totalPrice = 0.0;
    private String orderType = ""; // "Dine In" or "Take Away"

    public CoffeeOrderingApp() {
        frame = new JFrame("Coffee Ordering App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Category Panel
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(1, 3));

        JButton coffeeButton = new JButton("Coffee");
        coffeeButton.addActionListener(e -> loadCategory("Coffee"));

        JButton nonCoffeeButton = new JButton("Non-Coffee");
        nonCoffeeButton.addActionListener(e -> loadCategory("Non-Coffee"));

        JButton snacksButton = new JButton("Snacks");
        snacksButton.addActionListener(e -> loadCategory("Snacks"));

        categoryPanel.add(coffeeButton);
        categoryPanel.add(nonCoffeeButton);
        categoryPanel.add(snacksButton);

        frame.add(categoryPanel, BorderLayout.NORTH);

        // Menu Panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 3, 10, 10));
        menuPanel.setBackground(Color.WHITE);

        // Order Panel
        orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBackground(Color.WHITE);

        orderListModel = new DefaultListModel<>();
        JList<String> orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);

        totalLabel = new JLabel("Total: Rp0", JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> removeItem(orderList));

        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(e -> clearAllOrders());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(46, 204, 113)); // Green
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.addActionListener(e -> chooseOrderType()); // Choose "Dine In" or "Take Away"

        JPanel orderActionsPanel = new JPanel();
        orderActionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        orderActionsPanel.add(removeButton);
        orderActionsPanel.add(clearButton);
        orderActionsPanel.add(checkoutButton);

        orderPanel.add(orderScrollPane, BorderLayout.CENTER);
        orderPanel.add(totalLabel, BorderLayout.NORTH);
        orderPanel.add(orderActionsPanel, BorderLayout.SOUTH);

        // Load initial menu items
        loadCategory("Coffee");

        // Add menu and order panels to frame
        frame.add(new JScrollPane(menuPanel), BorderLayout.CENTER);
        frame.add(orderPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void loadCategory(String category) {
        menuPanel.removeAll();
        coffeeMenu = new ArrayList<>();

        // Example items for different categories
        if (category.equals("Coffee")) {
            coffeeMenu.add(new Coffee("Espresso", "30,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\americano.jpg")));
            coffeeMenu.add(new Coffee("Americano", "33,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\espresso.jpg")));
            coffeeMenu.add(new Coffee("Cappuccino", "35,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\cappuccino.jpg")));
        } else if (category.equals("Non-Coffee")) {
            coffeeMenu.add(new Coffee("Green Tea Latte", "40,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\greentea.jpg")));
            coffeeMenu.add(new Coffee("Milkshake Vanilla", "50,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\milkshake.jpg")));
        } else if (category.equals("Snacks")) {
            coffeeMenu.add(new Coffee("French Fries", "25,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\frenchfries.jpg")));
            coffeeMenu.add(new Coffee("Cheese Sticks", "30,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\cheesesticks.jpg")));
            coffeeMenu.add(new Coffee("Onion Rings", "28,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\onionrings.jpg")));
            coffeeMenu.add(new Coffee("Chocolate Cake", "35,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\chocolatecake.jpg")));
            coffeeMenu.add(new Coffee("Donut", "20,000", new ImageIcon("C:\\Users\\iGarrr\\IdeaProjects\\ttry\\src\\Gambar\\donut.jpg")));
        }

        for (Coffee coffee : coffeeMenu) {
            JPanel coffeePanel = new JPanel();
            coffeePanel.setLayout(new BorderLayout());
            coffeePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            coffeePanel.setBackground(Color.LIGHT_GRAY);

            // Set image
            Image image = coffee.image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));

            // Name and price
            JLabel nameLabel = new JLabel(coffee.name, JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel priceLabel = new JLabel("Rp" + coffee.price, JLabel.CENTER);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            priceLabel.setForeground(Color.GRAY);

            // Add button
            JButton addButton = new JButton("Pesan");
            addButton.setBackground(new Color(255, 165, 0)); // Orange
            addButton.setForeground(Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.addActionListener(e -> addToOrder(coffee));

            // Add to panel
            coffeePanel.add(imageLabel, BorderLayout.CENTER);
            coffeePanel.add(nameLabel, BorderLayout.NORTH);
            coffeePanel.add(priceLabel, BorderLayout.SOUTH);
            coffeePanel.add(addButton, BorderLayout.EAST);

            menuPanel.add(coffeePanel);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void addToOrder(Coffee coffee) {
        String itemName = coffee.name;
        String itemPrice = coffee.price;

        orderListModel.addElement(itemName + " - Rp" + itemPrice);
        totalPrice += Double.parseDouble(itemPrice.replace(",", ""));
        totalLabel.setText("Total: Rp" + formatPrice(totalPrice));
    }

    private void removeItem(JList<String> orderList) {
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex != -1) {
            // Get selected item details
            String selectedItem = orderListModel.get(selectedIndex);
            String[] parts = selectedItem.split(" - Rp");
            String priceString = parts[1].replace(",", ""); // Get price
            double price = Double.parseDouble(priceString);

            // Subtract item price from total
            totalPrice -= price;

            // Remove item from order list
            orderListModel.remove(selectedIndex);

            // Update total label
            totalLabel.setText("Total: Rp" + formatPrice(totalPrice));
        } else {
            JOptionPane.showMessageDialog(frame, "Please select an item to remove.");
        }
    }

    private void clearAllOrders() {
        orderListModel.clear();
        totalPrice = 0.0;
        totalLabel.setText("Total: Rp0");
    }

    private void chooseOrderType() {
        String[] options = {"Dine In", "Take Away"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Choose your order type:",
                "Order Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            orderType = "Dine In";
        } else if (choice == 1) {
            orderType = "Take Away";
        }

        if (!orderType.isEmpty()) {
            choosePaymentMethod();
        }
    }

    private void choosePaymentMethod() {
        String[] paymentMethods = {"Cash", "Credit Card", "E-Wallet"};
        String paymentMethod = (String) JOptionPane.showInputDialog(
                frame,
                "Select Payment Method:\nOrder Type: " + orderType,
                "Payment",
                JOptionPane.PLAIN_MESSAGE,
                null,
                paymentMethods,
                paymentMethods[0]);

        if (paymentMethod != null) {
            double tax = totalPrice * 0.12;
            double finalTotal = totalPrice + tax;

            // Build order details (like a receipt)
            StringBuilder orderDetails = new StringBuilder();
            for (int i = 0; i < orderListModel.size(); i++) {
                orderDetails.append(orderListModel.getElementAt(i)).append("\n");
            }

            // Show payment dialog with detailed receipt
            JOptionPane.showMessageDialog(frame,
                    "Order Type: " + orderType + "\n" +
                            "Payment Method: " + paymentMethod + "\n\n" +
                            "Order Details:\n" + orderDetails.toString() + "\n" +
                            "Subtotal: Rp" + formatPrice(totalPrice) + "\n" +
                            "Tax (12%): Rp" + formatPrice(tax) + "\n" +
                            "Total: Rp" + formatPrice(finalTotal));

            orderListModel.clear();
            totalPrice = 0.0;
            totalLabel.setText("Total: Rp0");
        }
    }

    private String formatPrice(double price) {
        return String.format("%,.0f", price).replace(',', '.');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CoffeeOrderingApp::new);
    }
}
