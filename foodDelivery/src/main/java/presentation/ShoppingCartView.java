package presentation;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartView extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());

    private GridBagConstraints c = new GridBagConstraints();

    private JTextField productName = new JTextField(10);
    private JTextField quantity = new JTextField(5);
    private JLabel buyLabel = new JLabel("BUY");



    private JLabel productNameLabel = new JLabel("Product Name: ");
    private JLabel quantityLabel = new JLabel("Quantity ");
    private JButton insertCartButton = new JButton("INSERT TO CART");
    private JButton updateCartButton = new JButton("UPDATE PRODUCT IN CART");
    private JButton deleteFromCartButton = new JButton("DELETE PRODUCT FROM CART");

    private JButton checkoutButton = new JButton("CHECKOUT");
    private JButton viewCartButton = new JButton("VIEW CART");
    private JButton viewProductsButton = new JButton("VIEW PRODUCTS");
    private JTable tabel = new JTable();private JTable tabel2 = new JTable();
    private MainController controller ;

    public ShoppingCartView(String name, MainController controller) {
        super(name);
        this.controller=controller;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(buyLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(productName, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(productNameLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        panel.add(quantity, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(quantityLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(insertCartButton, c);
        insertCartButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(updateCartButton, c);
        updateCartButton.addActionListener(controller);

        c.gridx = 2;
        c.gridy = 3;
        panel.add(deleteFromCartButton, c);
        deleteFromCartButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 4;
        panel.add(viewCartButton, c);
        viewCartButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 1;
        panel.add(viewProductsButton, c);
        viewProductsButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 6;
        panel.add(checkoutButton, c);
        checkoutButton.addActionListener(controller);

        this.add(panel);
    }
    public void addProductsTable(JTable table) {
        tabel.setVisible(false);
        this.tabel2=table;
        c.gridx=3;
        c.gridy=4;
        tabel.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public void addCartTabble(JTable table) {
        tabel.setVisible(false);
        this.tabel=table;
        c.gridx=1;
        c.gridy=5;
        tabel.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getProductName() {
        return productName;
    }

    public JTextField getQuantity() {
        return quantity;
    }
    public JLabel getBuyLabel() {
        return buyLabel;
    }
    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public JLabel getQuantityLabel() {
        return quantityLabel;
    }

    public JButton getInsertCartButton() {
        return insertCartButton;
    }

    public JButton getUpdateCartButton() {
        return updateCartButton;
    }

    public JButton getDeleteFromCartButton() {
        return deleteFromCartButton;
    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    public JTable getTabel() {
        return tabel;
    }

    public JButton getViewCartButton() {
        return viewCartButton;
    }

    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    public MainController getController() {
        return controller;
    }
}
