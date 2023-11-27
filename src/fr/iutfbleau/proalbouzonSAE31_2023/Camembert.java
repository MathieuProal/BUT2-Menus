package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Camembert</code> est la classe appellant la méthode principale du second programme.
 * @author Nathan Bouzon
 * @version 1.0
 */

public class Camembert extends JPanel {
    private double[] data;
    private Color[] colors;

/**
* Méthode invoquée a la création des camemberts rassemble les infortmations pour les construire. 
* @param data Tableau de type double[] dans lequel il regroupe les double qui permetent da calculer la séparation des parts
* @param colors Tableau de type Color[] dans lequel il regroupe les couleurs pour les parts du camembert
*/

    public void PieChartPanel(double[] data, Color[] colors) {
        this.data = data;
        this.colors = colors;
        repaint();
    } 

/**
* Méthode invoquée a la création des camemberts pour faire les dessins. 
* @param g est le pinceau avec lequel il va faire les dessins
*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinPieChart(g);
    }


/**
* Méthode invoquée qui a partir des informations prise crées un par un les morceau de camembert. 
* @param g est le pinceau avec lequel il va faire les dessins
*/
    private void dessinPieChart(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        int radius = Math.min(width, height) / 2;
        int centerX = width / 2;
        int centerY = height / 2;

        double total = 0.0;
        for (double value : data) {
            total += value;
        }
    
        double angleStart = 0.0;
        for (int i = 0; i < data.length; i++) {
            double angleExtent = 360.0 * data[i] / total;
            g.setColor(colors[i]);
            g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, (int) angleStart, (int) angleExtent);
            angleStart += angleExtent;
        }
    
    }
}