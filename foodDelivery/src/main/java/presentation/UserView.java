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

public class UserView extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());

    private GridBagConstraints c = new GridBagConstraints();
    private JTextField updateName = new JTextField(15);

    private JTextField updatePNC = new JTextField(10);
    private JTextField updateAddress = new JTextField(15);

    private JTextField updateUser = new JTextField(15);
    private JTextField updatePass = new JTextField(15);

    //private JTextField productName = new JTextField(10);
    //private JTextField quantity = new JTextField(5);

    private JButton personalInfoButton = new JButton("Personal Information");
  //  private JLabel buyLabel = new JLabel("BUY");

    private JLabel updateInfoLabel = new JLabel("Update personal information");


  //  private JLabel productNameLabel = new JLabel("Product Name: ");
  //  private JLabel quantityLabel = new JLabel("Quantity ");
    private JButton updateNameButton = new JButton("UPDATE NAME");
    private JButton updatePNCButton = new JButton("UPDATE PNC");
    private JButton updateAddressButton = new JButton("UPDATE ADDRESS");
    private JButton updateUerButton = new JButton("UPDATE USERNAME");
    private JButton updatePassButton = new JButton("UPDATE PASSWORD");
  //  private JButton insertCartButton = new JButton("INSERT TO CART");
  //  private JButton updateCartButton = new JButton("UPDATE PRODUCT IN CART");
   // private JButton deleteFromCartButton = new JButton("DELETE PRODUCT FROM CART");

    private //JButton checkoutButton = new JButton("CHECKOUT");
    JButton checkoutButton = new JButton("GO SHOPPING");
    JButton historyButton = new JButton("View History");
    private JTable tabel = new JTable();
    private JTable tabel2 = new JTable();
    private MainController controller ;

    public UserView(String name, MainController controller) {
        super(name);
        this.controller=controller;

        c.gridx = 0;
        c.gridy = 3;
        panel.add(personalInfoButton, c);
        personalInfoButton.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(updateInfoLabel, c);


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

        c.gridx = 3;
        c.gridy = 3;
        panel.add(historyButton, c);
        historyButton.addActionListener(controller);

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

       /*  c.gridx = 3;
        c.gridy = 0;
        panel.add(buyLabel, c);

       c.gridx = 3;
        c.gridy = 1;
        panel.add(productName, c);

        c.gridx = 4;
        c.gridy = 1;
        panel.add(productNameLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        panel.add(quantity, c);*/

      /*  c.gridx = 4;
        c.gridy = 2;
        panel.add(quantityLabel, c);

        c.gridx = 3;
        c.gridy = 3;
        panel.add(insertCartButton, c);
        insertCartButton.addActionListener(controller);

        c.gridx = 4;
        c.gridy = 3;
        panel.add(updateCartButton, c);
        updateCartButton.addActionListener(controller);

        c.gridx = 5;
        c.gridy = 3;
        panel.add(deleteFromCartButton, c);
        deleteFromCartButton.addActionListener(controller);
*/
        c.gridx = 1;
        c.gridy = 6;
        panel.add(checkoutButton, c);
        checkoutButton.addActionListener(controller);

        this.add(panel);
    }
    public void addTable(JTable table) {
        tabel.setVisible(false);
        this.tabel=table;
        c.gridx=0;
        c.gridy=6;
        tabel.setVisible(true);
        panel.add(table,c);
        this.pack();
    }

    public void addHistoryTable(JTable table) {
        tabel2.setVisible(false);
        this.tabel2=table;
        c.gridx=3;
        c.gridy=4;
        tabel2.setVisible(true);
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

  /*  public JTextField getProductName() {
        return productName;
    }

    public JTextField getQuantity() {
        return quantity;
    }
*/
    public JButton getPersonalInfoButton() {
        return personalInfoButton;
    }

    public JLabel getUpdateInfoLabel() {
        return updateInfoLabel;
    }

    public JButton getHistoryButton() {
        return historyButton;
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

    public JButton getUpdateUserButton() {
        return updateUerButton;
    }

    public JButton getUpdatePassButton() {
        return updatePassButton;
    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    public JTable getTabel() {
        return tabel;
    }

    public MainController getController() {
        return controller;
    }
}
