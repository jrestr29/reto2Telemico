
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sebastian
 */
public class DB {

    private Connection db;
    private String username;
    private String password;
    private String host;
    private String database;
    private Statement stmt;
    private ResultSet rs;

    public DB() {
        setUsername("root");
        setPassword("");
        setHost("localhost");
        setDatabase("reto2canales");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            db = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, username, password);
            db.createStatement();

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public ResultSet execQuery(String sql) {
        try{
            rs  = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("DB Query Error: " + e.getMessage());
        }
        
        return rs;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
