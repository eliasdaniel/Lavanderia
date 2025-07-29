package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Burbujas extends JPanel implements ActionListener {

    class Burbuja {
        int x, y, radio;
        float velocidad;
        Color color;

        public Burbuja(int x, int y, int radio, float velocidad, Color color) {
            this.x = x;
            this.y = y;
            this.radio = radio;
            this.velocidad = velocidad;
            this.color = color;
        }

        public void mover() {
            y += velocidad;
            if (y > getHeight()) {
                reiniciar();
            }
        }

        public void reiniciar() {
            Random rand = new Random();
            x = rand.nextInt(getWidth());
            y = -radio;
            radio = 10 + rand.nextInt(30);
            velocidad = 1 + rand.nextFloat() * 2;
            float h = rand.nextFloat();
            color = new Color(h, 0.4f + rand.nextFloat() * 0.3f, 1f, 0.25f + rand.nextFloat() * 0.3f); // translúcido pastel
        }
    }

    private final ArrayList<Burbuja> burbujas = new ArrayList<>();
    private final Timer timer;
    private final int cantidad = 30;

    public Burbujas() {
        setOpaque(false); // ✅ fondo del panel transparente
        inicializarBurbujas();
        timer = new Timer(30, this);
        timer.start();
    }

    private void inicializarBurbujas() {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            int radio = 10 + rand.nextInt(30);
            int x = rand.nextInt(800);
            int y = rand.nextInt(600);
            float velocidad = 1 + rand.nextFloat() * 2;
            float h = rand.nextFloat();
            Color color = new Color(h, 0.4f + rand.nextFloat() * 0.3f, 1f, 0.3f + rand.nextFloat() * 0.3f);
            burbujas.add(new Burbuja(x, y, radio, velocidad, color));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Burbuja b : burbujas) {
            g2.setColor(b.color);
            g2.fillOval(b.x, b.y, b.radio, b.radio);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Burbuja b : burbujas) {
            b.mover();
        }
        repaint();
    }
}
