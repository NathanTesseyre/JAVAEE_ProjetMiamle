/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nathan
 */
public final class MySQLConnection {
    
    private static Connection connection = null;
    
    private MySQLConnection(){
        
    }
    
    public static Connection getConnection() throws RuntimeException{
        if (connection == null) {
            String login = "groupe03";
            String password = "Fh4zoId2rCHX2";
            String host = "wp.ldnr.fr";
            String database = "groupe03";
            int port = 3306;
            String driver = "mysql";
            // Version produite : jdbc:mysql://localhost:8888/blog
            String url = "jdbc:" + driver + "://" + host + ":"
                    + port + "/" + database;
            // Premier appel : on doit construire la connexion
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                throw new RuntimeException("Connexion à la base de données impossible");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Driver " + driver + " inconnu.");
            }
        }
        return connection;
    }
    
    
}
