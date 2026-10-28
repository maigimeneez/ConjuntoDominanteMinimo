
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import modelo.Grafo;
import modelo.Solucion;

public class PanelGrafo extends JPanel {

    private Grafo _grafo;
    private Solucion _solucion;

    private static final int RADIO_VERTICE = 20;
    private static final int MARGEN = 50;

    public PanelGrafo() {

        _grafo = null;
        _solucion = null;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(700, 500));
    }

    public void actualizarGrafo(Grafo grafo, Solucion solucion) {

        _grafo = grafo;
        _solucion = solucion;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (_grafo == null) {
            return;
        }

        int cantidadVertices = _grafo.cantidadVertices();

        if (cantidadVertices == 0) {
            return;
        }

        int ancho = getWidth();
        int alto = getHeight();

        int centroX = ancho / 2;
        int centroY = alto / 2;

        int radio = Math.min(ancho, alto) / 2 - MARGEN;

        ArrayList<Integer> coordenadasX = new ArrayList<Integer>();
        ArrayList<Integer> coordenadasY = new ArrayList<Integer>();

        for (int i = 0; i < cantidadVertices; i++) {

            double angulo = 2 * Math.PI * i / cantidadVertices;

            int x = centroX + (int) (radio * Math.cos(angulo));
            int y = centroY + (int) (radio * Math.sin(angulo));

            coordenadasX.add(x);
            coordenadasY.add(y);
        }

        dibujarAristas(g2, coordenadasX, coordenadasY);
        dibujarVertices(g2, coordenadasX, coordenadasY);
    }

    private void dibujarAristas(
            Graphics2D g2,
            ArrayList<Integer> coordenadasX,
            ArrayList<Integer> coordenadasY) {

        g2.setColor(new Color(130, 140, 150));

        for (int vertice = 1; vertice <= _grafo.cantidadVertices(); vertice++) {

            ArrayList<Integer> vecinos = _grafo.obtenerVecinos(vertice);

            for (int i = 0; i < vecinos.size(); i++) {

                int vecino = vecinos.get(i);

                if (vertice < vecino) {

                    int x1 = coordenadasX.get(vertice - 1);
                    int y1 = coordenadasY.get(vertice - 1);

                    int x2 = coordenadasX.get(vecino - 1);
                    int y2 = coordenadasY.get(vecino - 1);

                    g2.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }

    private void dibujarVertices(
            Graphics2D g2,
            ArrayList<Integer> coordenadasX,
            ArrayList<Integer> coordenadasY) {

        for (int vertice = 1; vertice <= _grafo.cantidadVertices(); vertice++) {

            int x = coordenadasX.get(vertice - 1);
            int y = coordenadasY.get(vertice - 1);

            dibujarVertice(g2, vertice, x, y);
        }
    }

    private void dibujarVertice(Graphics2D g2, int vertice, int x, int y) {

        int diametro = RADIO_VERTICE * 2;

        if (_solucion != null && _solucion.contiene(vertice)) {

            g2.setColor(new Color(245, 165, 65));

        } else {

            g2.setColor(new Color(220, 230, 245));
        }

        g2.fillOval(
                x - RADIO_VERTICE,
                y - RADIO_VERTICE,
                diametro,
                diametro
        );

        g2.setColor(new Color(60, 90, 140));

        g2.drawOval(
                x - RADIO_VERTICE,
                y - RADIO_VERTICE,
                diametro,
                diametro
        );

        g2.setColor(Color.BLACK);

        g2.setFont(new Font("Arial", Font.BOLD, 14));

        String texto = String.valueOf(vertice);

        int anchoTexto = g2.getFontMetrics().stringWidth(texto);
        int altoTexto = g2.getFontMetrics().getAscent();

        g2.drawString(
                texto,
                x - anchoTexto / 2,
                y + altoTexto / 2 - 2
        );
    }
}
