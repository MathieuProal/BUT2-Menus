package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.*;
import java.awt.*;


/**
 * La classe <code>Fenetre</code> est la vue qui appelle les differentes classes dont elle a besoin pour faire tourner le programme.
 * @author Mathieu Proal
 * @version 1.0
 */
public class Fenetre extends JFrame {        
    BaseDonnees bd = new BaseDonnees();
    Proto prot = new Proto(bd);
    int solution=prot.getActionCorrecte(), protocole=prot.getProto(), menu=prot.getMenu();
    String tache=prot.getTache();
    
    Modele maud = new Modele(bd);


     /**
     * Le constructeur sert à créer la fenêtre et d'y ajouter les différents éléments (la question et l'arbre), ainsi que d'ajouter le listener de l'arbre
     */
    public Fenetre(){
        this.setTitle("Menu à tester");
        this.setSize(400,800);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel pano = new JLabel(this.tache);
        this.add(pano, BorderLayout.NORTH);

        this.bd.start(protocole);
        JTree arbre = maud.createTree(this.menu, null);
        JScrollPane scrollPane = new JScrollPane(arbre);
        
        Detect det = new Detect(arbre, this, bd, this.solution);

        this.add(scrollPane, BorderLayout.CENTER);

    }
}