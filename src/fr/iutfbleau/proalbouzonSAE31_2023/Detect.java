package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;


/**
 * La classe <code>Detect</code> est la classe qui gère les clics sur l'arbre, et déclenche les actions correspondantes.
 * @author Mathieu Proal
 * @version 1.0
 */
public class Detect implements TreeSelectionListener {
    private JTree arbre;
    private JFrame fenetre;
    private int aie_dit, actionCorrecte;
    BaseDonnees bd;
    int ordreSousMenu=0, reussite;
    
    /**
     * Le constructeur récupère tous les objets donnés en argument. Il en a besoin dans la suite du code.
     * @param abr l'arbre construit plus tôt
     * @param fen la fenetre affichée à l'écran
     * @param bédé l'objet BaseDonnees qui sert à faire les requetes
     * @param solution l'id de l'action attendue par le protocole, qui sera comparée à l'action choisie par l'utilisateur
     */
    public Detect(JTree abr, JFrame fen, BaseDonnees bédé, int solution){
        this.fenetre=fen;
        if (abr!=null){
            this.arbre=abr;
            this.arbre.addTreeSelectionListener(this);
        }
        this.bd=bédé;
        this.actionCorrecte=solution;
    }

    /**
     * Méthode qui se déclenche dès qu'un élément a été cliqué dans l'arbre que ce soit une feuille ou l'ouverture d'un sous-dossier.
     * Elle récupère le noeud cliqué le caste en type Element.
     * Elle récupère aussi l'id de l'élément cliqué pour le passer à la classe BaseDonnees avec l'ordre.
     * Si le noeud cliqué est une feuille, on retire le listener de l'arbre, on met à jour la variable réussite .
     * (si l'élément cliqué est bien celui qui était attendu ou non) et on la donne à la classe BaseDonnees, puis on arrête le programme.
     * @param e évènement
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if (this.arbre!=null){
            DefaultMutableTreeNode noeud = (DefaultMutableTreeNode)this.arbre.getLastSelectedPathComponent();
            Element elt= (Element)noeud.getUserObject();
            this.aie_dit = elt.recupId();
            this.bd.cliclic(this.aie_dit, this.ordreSousMenu);
            this.ordreSousMenu++;
            
            if (noeud.isLeaf()){
                if (this.actionCorrecte==this.aie_dit){
                    this.reussite=1;
                } else {
                    this.reussite=0;
                }

                this.bd.fin(reussite);
                this.fenetre.setVisible(false);
                System.exit(0);

            }
        }               
    }
}
