package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private final int canvasWidth = 400, canvasHeight = 400;
    private BufferedImage image;
    private Graphics2D offscreen;
    private List<Point> dots = new ArrayList<>(); // Lista de puncte

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        // Generăm punctele inițiale
        setDotCount(frame.configPanel.getDotCount());
    }

    private void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

        // Desenăm punctele
        for (Point p : dots) {
            g.setColor(Color.BLACK);
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
    }

    public void setDotCount(int count) {
        dots.clear(); // Ștergem punctele vechi
        Random rand = new Random();

        // Generăm puncte la poziții random pe ecran
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(canvasWidth - 20) + 10;
            int y = rand.nextInt(canvasHeight - 20) + 10;
            dots.add(new Point(x, y));
        }

        repaint(); // Redesenează panoul
    }
}