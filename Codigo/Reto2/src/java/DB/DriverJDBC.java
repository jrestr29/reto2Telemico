package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DriverJDBC {
    
    private static final String DBURL = "jdbc:mysql://31.170.166.58:3306/u511611292_reto2";
    private static final String USER = "u511611292_reto2";
    private static final String PASS = "eafit123"; 
    
    private JSONArray jsonArr;

    public DriverJDBC() {

        init();
    }
 
    private void init() {

        try {

            Connection conn = DriverManager.getConnection(DBURL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `canal`");
                        
            jsonArr = new JSONArray();
            
            while (rs.next()) { 
                
                String nombre = rs.getString("nombre");
                int id = rs.getInt("id");
                
                JSONObject jsonObj = new JSONObject();
                
                jsonObj.put("nombre", nombre);
                jsonObj.put("id", id);
                
                jsonArr.add(jsonObj);                                                          
            }                                                
               
        } catch (SQLException ex) {
            Logger.getLogger(DriverJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JSONArray getJsonArr() {
        return jsonArr;
    }        
}


