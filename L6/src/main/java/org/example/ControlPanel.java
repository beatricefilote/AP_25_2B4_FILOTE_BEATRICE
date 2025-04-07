package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(this::exitGame);

        add(exitBtn);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}