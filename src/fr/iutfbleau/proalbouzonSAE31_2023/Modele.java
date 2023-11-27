package fr.iutfbleau.proalbouzonSAE31_2023;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * La classe <code>Modele</code> est la classe qui sert à créer l'arbre.
 * @author Mathieu Proal
 * @version 1.0
 */
public class Modele {
    private BaseDonnees bd;
    private JTree arbre;
    private DefaultMutableTreeNode racine = new DefaultMutableTreeNode();

    /**
     * Le constructeur récupère l'objet BaseDonnees créé plus tôt dans la classe Fenetre.
     */
    public Modele(BaseDonnees bédé){
        this.bd=bédé;
    }

    /**
     * Méthode récursive qui permet de créer l'arbre. Elle ajoute noeud par noeud chaque élément de l'arbre, et dès qu'elle tombe sur 
     * un noeud ayant lui même un ou plusieurs fils, elle s'appelle elle-même pour ajouter le ou les fils en question, 
     * pour ensuite revenir là où elle en était.
     * Les noeuds passés en argument à DefaultMutableTreeNode sont de type Element, et contiennent un id et le nom correspondant
     * @param idpapa l'id du père des noeuds ajoutés pendant cette exécution
     * @param papa le noeud auquel il faut ajouter les éléments en cours
     * @return l'arbre complet pour que la vue puisse l'afficher
     */
    public JTree createTree(int idpapa, DefaultMutableTreeNode papa){
        Object tab[][]=this.bd.letsgo(idpapa);
        if (tab != null){
        
            for (int repet=0;repet<tab.length;repet++){
        
                Element elt = new Element(tab[repet][1], tab[repet][0]);
                elt.toString();
                DefaultMutableTreeNode composant = new DefaultMutableTreeNode(elt);

                if (papa == null){
                    racine.add(composant);
                } else {
                    papa.add(composant);
                }

                if ((int)tab[repet][2]==0){
                    createTree((int)tab[repet][0], composant);
                }
            }

            this.arbre= new JTree(racine);
            return arbre;

        }
        else {
            return null;
        }
    }

}
