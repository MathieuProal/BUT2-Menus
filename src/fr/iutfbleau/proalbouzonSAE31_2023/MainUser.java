package fr.iutfbleau.proalbouzonSAE31_2023;

/**
 * La classe <code>MainUser</code> est la classe appellant la méthode principale du premier programme.
 * @author Mathieu Proal
 * @version 1.0
 */
public class MainUser{
    /**
     * La méthode main appelle la classe Fenetre pour pouvoir commencer le programme.
     * @param args
     */
    public static void main(String[] args) {
        Fenetre fen = new Fenetre();
        fen.setVisible(true);
    }
}