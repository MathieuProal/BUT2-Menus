package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.*;

/**
 * La classe <code>Proto</code> est la classe appellant une méthode de BaseInfo.
 * @author Nathan Bouzon
 * @version 1.0
 */

public class ProtoDev {
    private int refProto;
    private int menu;
    private String tache;
    private int solution;    

/**
* Méthode invoquée a la création de la fenêtre pour faire fonctionner l'un des camenberts, 
* et permet donc basculer entre les deux cammenbert à l'aide de cardlayout et cardpanel.
* @param bd la base de données qu'il a besoin pour récupérer les informations
*/

    public ProtoDev(BaseInfo bd){
        Object[][] tableau=bd.recupProto();
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

/**
* Méthode invoquée lorque le protocole est choisit dans le programme dev. 
* @return l'ID du protocole choisit
*/
    public int getProto(){
        return this.refProto;
    }


/**
* Méthode invoquée lorque le protocole est choisit dans le programme dev. 
* @return le menu qui va avec du protocole choisit
*/

    public int getMenu(){
        return this.menu;
    }

/**
* Méthode invoquée lorque le protocole est choisit dans le programme dev. 
* @return le nom de tâche pour le protocole en question
*/

    public String getTache(){
        return this.tache;
    }

/**
* Méthode invoquée lorque le protocole est choisit dans le programme dev. 
* @return la solution que l'utilisateur doit chercher pour que le protocole soit correct
*/
    public int getActionCorrecte(){
        return this.solution;
    }
}
