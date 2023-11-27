package fr.iutfbleau.proalbouzonSAE31_2023;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe <code>ControlDev</code> est la classe qui s'occupe des détéction de la partie dev.
 * @author Nathan Bouzon
 * @version 1.0
 */

public class ControlDev implements ActionListener {
    boolean diagramme1 = true;
    JFrame fenetre;


/**
* Méthode invoquée a la création de la fenêtre, 
* @param fen fenêtre de la vue
*/
    public ControlDev(JFrame fen) {
        this.fenetre = fen;
    }

/**
* Méthode invoquée a la création de la fenêtre pour faire fonctionner le bouton "basculer", 
* et permet donc basculer entre les deux cammenbert à l'aide de cardlayout et cardpanel.
* @param e le detecteur qui permet de voir lorsque l'on clique sur le bouton "basculer"
*/
    @Override
    public void actionPerformed(ActionEvent e) {
        this.diagramme1 = !diagramme1;

        CardLayout cardLayout = ((Info) fenetre).getCardLayout();

        if (this.diagramme1) {
            cardLayout.show(((Info) fenetre).getCardPanel(), "camembert1");
        } else {
            cardLayout.show(((Info) fenetre).getCardPanel(), "camembert2");
        }
    }

/**
* Méthode invoquée lorque le bouton et cliquée pour le return, 
* @return un boolean qui permet de savoir lequel des deux camembert doit être en avant plan
*/
    public boolean queldiagramme() {
        return this.diagramme1;
    }
}