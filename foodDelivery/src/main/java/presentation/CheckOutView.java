package presentation;

import javax.swing.*;
import java.awt.*;

public class CheckOutView extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField card = new JTextField(30);
    private JLabel cardLabel = new JLabel("CARD NUMBER: ");
    private JButton cashButton = new JButton("CASH");
    private JButton cardButton = new JButton("CARD");
    private JButton finishButton = new JButton("FINISH");
    private MainController controller;

    public CheckOutView(String name, MainController controller) {
        cardLabel.setVisible(false);
        card.setVisible(false);
        finishButton.setVisible(false);
        c.gridx=2;
        c.gridy=1;
        panel.add(cashButton,c);
        cashButton.addActionListener(controller);

        c.gridx=3;
        c.gridy=1;
        panel.add(cardButton,c);
        cardButton.addActionListener(controller);

        this.add(panel);
    }
    public void addCard() {
        c.gridx=0;
        c.gridy=0;
        cardLabel.setVisible(true);
        panel.add(cardLabel,c);

        c.gridx=0;
        c.gridy=1;
        card.setVisible(true);
        panel.add(card,c);

        c.gridx=0;
        c.gridy=2;
        finishButton.setVisible(true);
        finishButton.addActionListener(controller);
        panel.add(finishButton,c);

        this.pack();
    }
    public JPanel getPanel() {
        return panel;
    }

    public JTextField getCard() {
        return card;
    }

    public JLabel getCardLabel() {
        return cardLabel;
    }

    public JButton getCashButton() {
        return cashButton;
    }

    public JButton getCardButton() {
        return cardButton;
    }

    public JButton getFinishButton() {
        return finishButton;
    }
}
