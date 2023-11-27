package fr.iutfbleau.proalbouzonSAE31_2023;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>BaseDonnees</code> est la classe qui s'occupe de toutes les requêtes SQL.
 * @author Mathieu Proal
 * @version 1.0
 */
public class BaseDonnees {
    Connection cnx;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs; 
    ResultSet rs2;
    Object[][] tab;
    int action;
    int resid;

    /**
     * Méthode invoquée au début du programme. Elle créeW le tuple dans la base SAE31_Resultat, en lui donnant un id resultat et l'id du
     * protocole choisi par l'utilisateur.
     * De plus, l'attribut Reussite est initialisé à 2. Si l'utilisateur clique sur l'option attendue, il passera à 1, et à 0 s'il échoue.
     * Cet attribut restera à 2 si l'utilisateur ne clique sur aucun attribut, ou que le programme s'arrête.
     * @param protocole l'id du protocole choisi par l'utilisateur
     */
    public void start(int protocole){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                try { 
                    pst = cnx.prepareStatement("Select MAX(ResultatID) FROM SAE31_Resultat;");
                    rs=pst.executeQuery();
                    rs.next();
                    this.resid=rs.getInt(1)+1;
                    pst = cnx.prepareStatement("Insert into SAE31_Resultat VALUES (?,?,2)");
                    pst.setInt(1,this.resid);
                    pst.setInt(2,protocole);
                    pst.executeUpdate();

                    try {                                        
                        pst.close();
                        rs.close();
                        cnx.close();
                        
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Méthode qui récupère toutes les informations des protocoles dans la table SAE31_Protocole. Cela permettra à l'utilisateur
     * de choisir le protocole à utiliser.
     * @return un tableau de type Object[][] qui contient toutes les informations de la table SAE31_Protocole
     */
    public Object[][] recupProto(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                try { 
                        pst = cnx.prepareStatement("SELECT * FROM SAE31_Protocole;");
                        pst2 = cnx.prepareStatement("SELECT COUNT(*) FROM SAE31_Protocole;");
                        rs = pst.executeQuery();
                        rs2 = pst2.executeQuery();
                        int taille;
                        rs2.next();
                        taille=rs2.getInt(1);
                        this.tab = new Object[taille][5];
                        int i=0;
                        while (rs.next()) {
                            this.tab[i][0]=rs.getInt(1);
                            this.tab[i][1]=rs.getString(2);
                            this.tab[i][2]=rs.getInt(3);
                            this.tab[i][3]=rs.getString(4);
                            this.tab[i][4]=rs.getInt(5);
                            i++;
                        }
                                    
                        try {                                        
                            pst.close();
                            rs.close();
                            pst2.close();
                            rs2.close();
                            cnx.close();
                            
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                            return null;
                        }


                    } catch(SQLException e) {
                        JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return this.tab;
    }

    
    /**
     * Méthode qui est invoquée au moment de la création de l'arbre. Elle récupère tous les fils d'un élément (selon son id) dans 
     * la table SAE31_Element.
     * @param idpapa id de l'élément dont on veut récupérer les fils
     * @return un tableau de type Object[][] qui contient toutes les informations des fils d'un élément
     */
    public Object[][] letsgo(int idpapa){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                try { 
                    pst = cnx.prepareStatement("SELECT * FROM SAE31_Element WHERE PereID=? ORDER BY Ordre;");
                    pst.setInt(1,idpapa);
                    pst2 = cnx.prepareStatement("SELECT COUNT(*) FROM SAE31_Element WHERE PereID=?;");
                    pst2.setInt(1,idpapa);
                    rs = pst.executeQuery();
                    rs2 = pst2.executeQuery();
                    int taille;
                    rs2.next();
                    taille=rs2.getInt(1);
                    this.tab = new Object[taille][5];
                    int i=0;
                    while (rs.next()) {
                        this.tab[i][0]=rs.getInt(1);
                        this.tab[i][1]=rs.getString(2);
                        this.tab[i][2]=rs.getInt(3);
                        this.tab[i][3]=rs.getInt(4);
                        this.tab[i][4]=rs.getInt(5);
                        i++;
                    }
                                
                    try {                                        
                        pst.close();
                        rs.close();
                        pst2.close();
                        rs2.close();
                        cnx.close();
                        
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }

                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return this.tab;
    }

    /**
     * Méthode invoquée dès qu'un élément de l'arbre est cliqué. Cet élément est ajouté dans la table SAE31_ElementClique, avec l'id du resultat en cours,
     * ainsi que l'indice dans lequel cet élément a été cliqué. Ce dernier attribut permet de savoir dans quel ordre ont été cliqués les sous-menus
     * @param id l'id de l'élément qui a été cliqué
     * @param ordre l'indice de clic
     */
    public void cliclic(int id, int ordre){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                try {
                    pst = cnx.prepareStatement("Insert into SAE31_ElementClique VALUES (?,?,?)");
                    pst.setInt(1,id);
                    pst.setInt(2,this.resid);
                    pst.setInt(3,ordre);
                    pst.executeUpdate();

                    try {                                        
                        pst.close();
                        cnx.close();
                        
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    

    /**
     * Méthode invoquée à la toute fin du programme. Elle met à jour la table SAE31_Resultat en fonction de la réussite ou non de l'utilisateur
     * (selon s'il a cliqué ou non sur l'option attendue par le protocole).
     * @param reussite variable valant 0 ou 1, selon la réussite de l'utilisateur
     */
    public void fin(int reussite){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                try { 
                        pst = cnx.prepareStatement("Update SAE31_Resultat set Reussite=? WHERE ResultatID=?;");
                        pst.setInt(1,reussite);
                        pst.setInt(2,this.resid);
                        pst.executeUpdate(); 

                        try {                                        
                            pst.close();
                            cnx.close();
                            
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    } catch(SQLException e) {
                        JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}