package fr.iutfbleau.proalbouzonSAE31_2023;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>BaseInfo</code> est la classe qui s'occupe de toutes les requêtes SQL de la partie dev.
 * @author Nathan Bouzon
 * @version 1.0
 */

public class BaseInfo {
    Connection cnx;
    PreparedStatement pst, pst2, pst3;
    ResultSet rs, rs2, rs3; 
    int[][] tab2;
    int[] tab1, tab3;
    Object[][] tab;
    int correct, incorrect, quitte,i;
    int taille1, taille2;


/**
* Méthode invoquée au début du programme pour récupérer les informations de la table SAE1_ResultatID. 
* avec le protocole choisi par l'utilisateur pour faire le camembert 1.
* @return un tableau de type int[][] qui contient toutes les informations de la table SAE31_resultatID
* @param idProto l'id du protocole choisi par l'utilisateur
*/
    public int[][] informationBase1(int idProto){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal", "mehackpasstp7!");

                //Select
                try { 
                        pst = cnx.prepareStatement("SELECT * FROM SAE31_Resultat WHERE ProtocoleID=?;");
                        pst.setInt(1,idProto);
                        pst2 = cnx.prepareStatement("SELECT COUNT(*) FROM SAE31_Resultat;");
                      
                        //Execution
                        try { 
                            rs = pst.executeQuery();
                            rs2 = pst2.executeQuery();
                           
                                rs2.next();
                                taille2=rs2.getInt(1);

                                //Utilisation
                                this.tab2 = new int[taille2][3];
                                int i=0;
    
                                    while (rs.next()) {
                                        this.tab2[i][0]=rs.getInt(1);
                                        this.tab2[i][1]=rs.getInt(2);
                                        this.tab2[i][2]=rs.getInt(3);
                                        
                                        if(1 == this.tab2[i][2]){
                                            this.correct++;
                                        }else if(0 == this.tab2[i][2]){
                                            this.incorrect++;
                                        }else{
                                            this.quitte++;
                                        }
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
                            }

                        } catch(SQLException e) {
                            JOptionPane.showMessageDialog(null, "Aucune données !","Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch(SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
        }
        return this.tab2;
    }
/**
* Méthode invoquée au début par info.java pour le camenbert 1, 
* @return un int "correct" qui contient le nombre de fois ou l'utilisateur a réussi le test du protocole choisi,
*/
    public int getCor(){
        return this.correct;
    }
/**
* Méthode invoquée au début par info.java pour le camenbert 1, 
* @return un int "incorrect" qui contient le nombre de fois ou l'utilisateur a échoué le test du protocole choisi,
*/
    public int getIncor(){
        return this.incorrect;
    }

/**
* Méthode invoquée au début par info.java pour le camenbert 1, 
* @return un int "quitte" qui contient le nombre de fois ou l'utilisateur a quitter le test du protocole choisi en cours de route,
*/
    public int getQuit(){
        return this.quitte;
    }
    

/**
* Méthode invoquée au début du programme pour récupérer les informations de la table SAE1_ResultatID, 
* avec le protocole choisi par l'utilisateur pour faire le camembert 2.
* @return un tableau de type int[] qui contient le compte des tuplus de la table SAE31_resultatID qui à pour protocole idproto,
* @param idProto l'id du protocole choisi par l'utilisateur
*/

    public int[] informationBase2(int idProto){
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                cnx = DriverManager.getConnection
                ("jdbc:mariadb://dwarves.iut-fbleau.fr/proal", "proal","mehackpasstp7!");
                
                try{  

                    pst = cnx.prepareStatement("SELECT COUNT(*) FROM SAE31_Resultat WHERE ProtocoleID=?;");
                    pst.setInt(1,idProto);
                    rs = pst.executeQuery();
                    rs.next();
                    taille1=rs.getInt(1);
                    this.tab1 = new int[taille1];
                    this.tab3 = new int[taille1];

                    pst = cnx.prepareStatement("SELECT ResultatID FROM SAE31_Resultat WHERE ProtocoleID=?;");
                    pst.setInt(1,idProto);
                    rs = pst.executeQuery();
                    int indice=0;
                    while(rs.next()){
                        this.tab3[indice]=rs.getInt(1);
                        indice++;
                    }

                    for(int tour = 0; tour<taille1; tour++){
                        pst = cnx.prepareStatement("SELECT COUNT(*) FROM SAE31_ElementClique el, SAE31_Resultat res WHERE el.ResultatID=res.ResultatID AND res.ResultatID=? and Ordre not in (SELECT MAX(Ordre) from SAE31_ElementClique el2 where el2.ResultatID=?);");
                        pst.setInt(1,tab3[tour]);
                        pst.setInt(2,tab3[tour]);
                        rs = pst.executeQuery();
                        rs.next();
                        if((rs.getInt(1)) != 0){
                            this.tab1[tour]=rs.getInt(1);
                        }
                    }
                    
                    try {                                        
                        cnx.close();

                    } catch (SQLException e) {
                         JOptionPane.showMessageDialog(null, "Erreur lors de la fermeture des ressources","Error", JOptionPane.ERROR_MESSAGE);
                    }
            
                } catch (SQLException e) {
                     JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
           JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
        }
        return this.tab1;
    }


/**
* Méthode invoquée un peu plus tard pour récuperer les informations de la table SAE31_Protocole, 
* pour les séparer pas la suite.
* @return un tableau de type Objet[][] qui contient la table SAE31_Protocole,
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
                        }


                    } catch(SQLException e) {
                        JOptionPane.showMessageDialog(null, "Mauvaise utilisation","Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erreur lors de la connexion !","Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Pilote non disponible !","Error", JOptionPane.ERROR_MESSAGE);
        }
        return this.tab;
    }
}
