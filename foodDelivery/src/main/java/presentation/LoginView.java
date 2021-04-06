package presentation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class LoginView extends JFrame{
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField username = new JTextField(30);
    private JLabel usernameLabel = new JLabel("Username: ");
    private JTextField password = new JTextField(30);
    private JLabel passwordLabel = new JLabel("Password: ");
    private JButton loginUser = new JButton("LOGIN AS USER");
    private JButton loginAdmin = new JButton("LOGIN AS ADMIN");


    private JTable tabel = new JTable();
    private JButton signUp = new JButton("SIGN UP");
    private MainController controller;

    public LoginView(String name, MainController controller) {
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


        c.gridx=2;
        c.gridy=0;
        panel.add(loginUser,c);
        loginUser.addActionListener(controller);

        c.gridx=2;
        c.gridy=1;
        panel.add(loginAdmin,c);
        loginAdmin.addActionListener(controller);


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

    public JButton getLoginUser() {
        return loginUser;
    }

    public JButton getLoginAdmin() {
        return loginAdmin;
    }

    public JButton getSignUp() {
        return signUp;
    }


}

