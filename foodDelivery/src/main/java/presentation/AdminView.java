package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminView extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());

    private GridBagConstraints c = new GridBagConstraints();
    private JTextField updateName = new JTextField(15);

    private JTextField updatePNC = new JTextField(15);
    private JTextField updateAddress = new JTextField(15);

    private JTextField updateUser = new JTextField(15);
    private JTextField updatePass = new JTextField(15);
    private JTextField idUser = new JTextField(15);

    private JButton viewUsersButton = new JButton("View all users");
    private JButton makeDiscoutButton = new JButton("Discount for user");
    private JButton generateReportButton = new JButton("Generate Report");
    private JLabel buyLabel = new JLabel("BUY");

    private JLabel updateInfoLabel = new JLabel("ID Customer");

    private JLabel idProductLabel = new JLabel("ID Product");

    private JButton updateNameButton = new JButton("UPDATE NAME");
    private JButton updatePNCButton = new JButton("UPDATE PNC");
    private JButton updateAddressButton = new JButton("UPDATE ADDRESS");
    private JButton updateUerButton = new JButton("UPDATE USERNAME");
    private JButton updatePassButton = new JButton("UPDATE PASSWORD");

    private JButton insertProductButton = new JButton("INSERT PRODUCT");
    private JButton deleteProductButton = new JButton("DELETE PRODUCT");

    private JButton insertCustomerButton = new JButton("INSERT USER");
    private JButton deleteCustomerButton = new JButton("DELETE USER");

    private JButton viewProductsButton = new JButton("View all Products");

    private JTextField idProduct = new JTextField(15);
    private JTextField updateProductName = new JTextField(15);
    private JTextField updatePrice = new JTextField(15);
    private JTextField updateQuantity = new JTextField(15);
    private JTextField updateDescription = new JTextField(15);

    private JButton updateProductNameButton = new JButton("UPDATE PRODUCT NAME");
    private JButton updatePriceButton = new JButton("UPDATE PRICE");
    private JButton updateQuantityButton = new JButton("UPDATE QUANTITY");
    private JButton updateDescriptionButton = new JButton("UPDATE DESCRIPTION");

    private JTextField dateFrom = new JTextField(15);
    private JTextField dateTo = new JTextField(15);

    private JTable tabel = new JTable();
    private JTable tabel2 = new JTable();
    private JTable tabel3 = new JTable();
    private MainController controller ;

    public AdminView(String name, MainController controller) {
        super(name);
        this.controller=controller;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(viewUsersButton, c);
        viewUsersButton.addActionListener(controller);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(insertCustomerButton, c);
        insertCustomerButton.addActionListener(controller);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(makeDiscoutButton, c);
        makeDiscoutButton.addActionListener(controller);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(generateReportButton, c);
        generateReportButton.addActionListener(controller);

        c.gridx = 0;
        c.gridy = 5;
        panel.add(dateFrom, c);

        c.gridx = 0;
        c.gridy = 6;
        panel.add(dateTo, c);

        c.gridx = 2;
        c.gridy = 0;
        panel.add(updateInfoLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(idUser, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(updateName, c);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(updateNameButton, c);
        updateNameButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 2;
        panel.add(updatePNC, c);

        c.gridx = 2;
        c.gridy = 2;
        panel.add(updatePNCButton, c);
        updatePNCButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(updateAddress, c);

        c.gridx = 2;
        c.gridy = 3;
        panel.add(updateAddressButton, c);
        updateAddressButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 4;
        panel.add(updateUser, c);

        c.gridx = 2;
        c.gridy = 4;
        panel.add(updateUerButton, c);
        updateUerButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 5;
        panel.add(updatePass, c);

        c.gridx = 2;
        c.gridy = 5;
        panel.add(updatePassButton, c);
        updatePassButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 6;
        panel.add(deleteCustomerButton, c);
        deleteCustomerButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 0;
        panel.add(idProduct, c);

        c.gridx = 4;
        c.gridy = 0;
        panel.add(idProductLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        panel.add(updateProductName, c);

        c.gridx = 4;
        c.gridy = 1;
        panel.add(updateProductNameButton, c);
        updateProductNameButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 2;
        panel.add(updateQuantity, c);

        c.gridx = 4;
        c.gridy = 2;
        panel.add(updateQuantityButton, c);
        updateQuantityButton.addActionListener(controller);


        c.gridx = 3;
        c.gridy = 3;
        panel.add(updatePrice, c);

        c.gridx = 4;
        c.gridy = 3;
        panel.add(updatePriceButton, c);
        updatePriceButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 4;
        panel.add(updateDescription, c);

        c.gridx = 4;
        c.gridy = 4;
        panel.add(updateDescriptionButton, c);
        updateDescriptionButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 5;
        panel.add(insertProductButton, c);
        insertProductButton.addActionListener(controller);

        c.gridx = 4;
        c.gridy = 5;
        panel.add(deleteProductButton, c);
        deleteProductButton.addActionListener(controller);

        c.gridx = 3;
        c.gridy = 6;
        panel.add(viewProductsButton, c);
        viewProductsButton.addActionListener(controller);

        this.add(panel);
    }

    public void addReportTable(JTable table) {
        tabel3.setVisible(false);
        this.tabel3=table;
        c.gridx=0;
        c.gridy=7;
        tabel3.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public void addProductsTable(JTable table) {
        tabel2.setVisible(false);
        this.tabel2=table;
        c.gridx=4;
        c.gridy=6;
        tabel2.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public void addCustomersTable(JTable table) {
        tabel.setVisible(false);
        this.tabel=table;
        c.gridx=0;
        c.gridy=1;
        tabel.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getUpdateName() {
        return updateName;
    }

    public JTextField getUpdatePNC() {
        return updatePNC;
    }

    public JTextField getUpdateAddress() {
        return updateAddress;
    }

    public JTextField getUpdateUser() {
        return updateUser;
    }

    public JTextField getUpdatePass() {
        return updatePass;
    }

    public JTextField getUpdateProductName() {
        return updateProductName;
    }

    public JTextField getUpdateQuantity() {
        return updateQuantity;
    }

    public JButton getViewUsersButton() {
        return viewUsersButton;
    }

    public JLabel getBuyLabel() {
        return buyLabel;
    }

    public JLabel getUpdateInfoLabel() {
        return updateInfoLabel;
    }

    public JTextField getDateFrom() {
        return dateFrom;
    }

    public JTextField getDateTo() {
        return dateTo;
    }

    public JButton getUpdateNameButton() {
        return updateNameButton;
    }

    public JButton getUpdatePNCButton() {
        return updatePNCButton;
    }

    public JButton getUpdateAddressButton() {
        return updateAddressButton;
    }

    public JButton getUpdateUerButton() {
        return updateUerButton;
    }

    public JButton getUpdatePassButton() {
        return updatePassButton;
    }
    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    public JTable getTabel() {
        return tabel;
    }

    public JLabel getIdProductLabel() {
        return idProductLabel;
    }

    public JButton getInsertProductButton() {
        return insertProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public JTextField getIdProduct() {
        return idProduct;
    }

    public JTextField getUpdatePrice() {
        return updatePrice;
    }

    public JTextField getUpdateDescription() {
        return updateDescription;
    }

    public JButton getUpdateProductNameButton() {
        return updateProductNameButton;
    }

    public JButton getUpdatePriceButton() {
        return updatePriceButton;
    }

    public JButton getUpdateQuantityButton() {
        return updateQuantityButton;
    }

    public JButton getUpdateDescriptionButton() {
        return updateDescriptionButton;
    }

    public MainController getController() {
        return controller;
    }

    public JTextField getIdUser() {
        return idUser;
    }

    public JButton getInsertCustomerButton() {
        return insertCustomerButton;
    }

    public JButton getDeleteCustomerButton() {
        return deleteCustomerButton;
    }

    public JButton getMakeDiscoutButton() {
        return makeDiscoutButton;
    }

    public JButton getGenerateReportButton() {
        return generateReportButton;
    }
}
