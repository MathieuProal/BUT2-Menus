package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.*;

/**
 * La classe <code>Proto</code> est la classe qui donne le choix à l'utilisateur quel protocole utiliser
 * @author Mathieu Proal
 * @version 1.0
 */
public class Proto {
    private int refProto;
    private int menu;
    private String tache;
    private int solution;    

    /**
     * Le constructeur récupère les informations des protocoles, et demande à l'utilisateur lequel il veut utiliser.
     */
    public Proto(BaseDonnees bd){
        Object[][] tableau=bd.recupProto();
        if (tableau != null){
            String[] choix = new String[tableau.length];
            for (int repet=0;repet<tableau.length;repet++){
                choix[repet]=(String)tableau[repet][1];
            }
            int reponse = JOptionPane.showOptionDialog(null, "Quel protocole voulez-vous suivre ?", 
            "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, null);
            if (reponse==-1){
                System.err.println("Veuillez indiquer un protocole");
                System.exit(0);
            }
            this.refProto=(int)tableau[reponse][0];
            this.menu=(int)tableau[reponse][2];
            this.tache=(String)tableau[reponse][3];
            this.solution=(int)tableau[reponse][4];
        }
    }

    /**
     * Méthode qui permet de récupérer l'id du protocole choisi.
     * @return l'id du protocole choisi
     */
    public int getProto(){
        return this.refProto;
    }

    /**
     * Méthode qui permet de récupérer l'id de la racine du menu correspondant au protocole choisi.
     * @return l'id de la racine du menu correspondant au protocole choisi>
     */
    public int getMenu(){
        return this.menu;
    }

    /**
     * Méthode qui permet de récupérer la description de la tâche du protocole choisi.
     * @return la description de la tâche du protocole choisi
     */
    public String getTache(){
        return this.tache;
    }

    /**
     * Méthode qui permet de récupérer l'id de l'action attendue par le protocole.
     * @return l'id de l'action attendue par le protocole
     */
    public int getActionCorrecte(){
        return this.solution;
    }
}
