package presentation;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField username = new JTextField(30);
    private JLabel usernameLabel = new JLabel("Username: ");
    private JTextField password = new JTextField(30);
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField customerName = new JTextField(30);
    private JLabel nameLabel = new JLabel("Name: ");
    private JTextField address = new JTextField(30);
    private JLabel addressLabel = new JLabel("Address: ");
    private JTextField pnc = new JTextField(30);
    private JLabel pncLabel = new JLabel("PNC: ");


    private JTable tabel = new JTable();
    private JButton signUp = new JButton("SIGN UP");
    private MainController controller;

    public SignUpView(String name, MainController controller) {
        super(name);
        this.controller=controller;
        c.gridx=1;
        c.gridy=0;
        panel.add(username,c);

        c.gridx=1;
        c.gridy=1;
        panel.add(password,c);

        c.gridx=0;
        c.gridy=0;
        panel.add(usernameLabel,c);

        c.gridx=0;
        c.gridy=1;
        panel.add(passwordLabel,c);

        c.gridx=1;
        c.gridy=2;
        panel.add(customerName,c);

        c.gridx=1;
        c.gridy=3;
        panel.add(address,c);

        c.gridx=0;
        c.gridy=2;
        panel.add(nameLabel,c);

        c.gridx=0;
        c.gridy=3;
        panel.add(addressLabel,c);

        c.gridx=1;
        c.gridy=4;
        panel.add(pnc,c);

        c.gridx=0;
        c.gridy=4;
        panel.add(pncLabel,c);


        c.gridx=2;
        c.gridy=3;
        panel.add(signUp,c);
        signUp.addActionListener(controller);

        this.add(panel);
    }
    public void addTable(JTable table) {
        tabel.setVisible(false);
        this.tabel=table;
        c.gridx=1;
        c.gridy=4;
        tabel.setVisible(true);
        panel.add(table,c);
        this.pack();
    }
    public JPanel getPanel() {
        return panel;
    }

    public JTextField getUsername() {
        return username;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JTextField getPassword() {
        return password;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getAddress() {
        return address;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public JTextField getPnc() {
        return pnc;
    }

    public JLabel getPncLabel() {
        return pncLabel;
    }

    public JButton getSignUp() {
        return signUp;
    }
}
