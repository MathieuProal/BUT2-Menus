package fr.iutfbleau.proalbouzonSAE31_2023;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>Info</code> est la classe qui s'occupe de toutes la vue de la fenêtre.
 * @author Nathan Bouzon
 * @version 1.0
 */

public class Info extends JFrame {
    private ControlDev controlDev;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String stat; 


/**
* Méthode invoquée a la création des camembert, 
* et permet de créer des couleurs aléatoire pour le second camembert.
* @return donne la couleur en question
*/
    private Color getCouleur(){
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
    
        return new Color(r, g, b);
    } 

/**
* Méthode invoquée pour création de la fenêtre et appel son fonctionnement. 
*/
    public Info(){
        
        this.setTitle("Informations DEV");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLayout(cardLayout);
        
        BaseInfo test = new BaseInfo();
        ProtoDev ptdv = new ProtoDev(test);
        int idProto = ptdv.getProto();

        int[][] tab1 = test.informationBase1(idProto);
        int[] tab2 = test.informationBase2(idProto);

        int correctc = test.getCor();
        int incorrectc = test.getIncor();
        int quittec = test.getQuit();

        this.controlDev = new ControlDev(this);
        cardLayout = new CardLayout();
        JButton bouton = new JButton("Basculer");

        cardPanel= new JPanel(cardLayout);
        String morceau1 = "Nombre de sous-menu ouverts (0 = erreur du protocole). ";
        String morceau2 = "Voici les résultats de différents essais: ";
        JPanel cam1= new JPanel();
        JPanel cam2= new JPanel();
        bouton.addActionListener(controlDev);


        JLabel texte = new JLabel("Vert = Correct ("+correctc+"), Rouge = Incorrect ("+incorrectc+"), Bleu = Erreur ("+quittec+")");
        
        double[] data2 = new double[tab2.length];;
        for(int i=0; i<tab2.length;i++){
            data2[i] = (double) tab2[i];
        }
       
        Color[] colors2 = new Color[data2.length];
       
        for (int i = 0; i < data2.length; i++) {
            colors2[i] = getCouleur();
        }
        
        for(int i=0; i<tab2.length;i++){
            String stat = String.valueOf(tab2[i]);
            if(i<=tab2.length-2){
             morceau2 += stat + ", ";
            }else{
              morceau2 += "fin.";
            }
        }
        JLabel legende1 = new JLabel(morceau1);
        JLabel legende2 = new JLabel(morceau2);

        double[] data1 = {correctc, incorrectc, quittec};
        Color[] colors1 = {Color.GREEN, Color.RED, Color.BLUE};
       
        Camembert pieChart1 = new Camembert();
        pieChart1.PieChartPanel(data1, colors1);
        Camembert pieChart2 = new Camembert();
        pieChart2.PieChartPanel(data2, colors2);
        
        boolean queldiagramme = controlDev.queldiagramme();
            
            cam1.setLayout(null);
            cam1.add(texte);
            texte.setSize(350,30);
            texte.setLocation((this.getWidth()-texte.getWidth())/2,0);
            cam1.add(pieChart1);
            pieChart1.setLocation(this.getWidth()/4,texte.getHeight()+10);
            pieChart1.setSize(this.getHeight()/2,this.getWidth()/2);

            cam2.setLayout(null);
            cam2.add(legende1);
            cam2.add(legende2);
            legende1.setSize(350,30);
            legende1.setLocation((this.getWidth()-texte.getWidth())/2,0);
            legende2.setSize(350,30);
            legende2.setLocation((this.getWidth()-texte.getWidth())/2,0+20);
            cam2.add(pieChart2);
            pieChart2.setLocation(this.getWidth()/4,50);
            pieChart2.setSize(this.getHeight()/2,this.getWidth()/2);
            
            cardPanel.add(cam1,"camembert1");
            cardPanel.add(cam2,"camembert2");
            cardLayout.show(cardPanel, "camembert1");


        this.setLayout(new BorderLayout());
        this.add(cardPanel, BorderLayout.CENTER);
        this.add(bouton, BorderLayout.SOUTH);

    }

/**
* Méthode pour connaitre qui est le cardLayout.
* @return le cardLayout en question
*/
    public CardLayout getCardLayout() {
        return cardLayout;
    }
    
/**
* Méthode pour connaitre qui est le CardPanel en cours d'utilisation.
* @return le cardPanel en question
*/
    public JPanel getCardPanel() {
        return cardPanel;
    }
}


