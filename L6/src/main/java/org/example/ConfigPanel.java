package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        // Adăugăm un listener pentru a actualiza punctele când utilizatorul schimbă valoarea
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                frame.canvas.setDotCount((int) spinner.getValue());
            }
        });

        add(label);
        add(spinner);
    }

    public int getDotCount() {
        return (int) spinner.getValue();
    }
}