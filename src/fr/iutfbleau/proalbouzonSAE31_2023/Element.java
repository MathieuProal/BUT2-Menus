package fr.iutfbleau.proalbouzonSAE31_2023;


/**
 * La classe <code>Element</code> est la classe dont les objets seront contenus dans les noeuds de l'arbre.
 * Ils ont un id et un nom
 * @author Mathieu Proal
 * @version 1.0
 */
public class Element {
    String snom;
    int sid;

    /**
     * Le constructeur récupère le nom et l'id donné par Modele et les caste.
     * @param nom récupère le nom donné dans la classe Modele
     * @param id récupère l'id donné dans la classe Modele
     */
    public Element(Object nom, Object id){
        this.snom=(String)nom;
        this.sid=(int)id;
    }

    /**
     * Méthode qui retourne l'attribut nom de l'objet
     * @return le nom de l'objet
     */
    public String recupNom(){
        return this.snom;
    }

    /**
     * Méthode qui retourne le nom de l'objet pour que seul le nom s'affiche dans la fenêtre
     * @return le nom de l'objet
     */
    @Override
    public String toString(){
        return this.snom;
    }

    /**
     * Méthode qui retourne l'attribut id de l'objet
     * @return l'id de l'objet
     */
    public int recupId(){
        return this.sid;
    }
}
