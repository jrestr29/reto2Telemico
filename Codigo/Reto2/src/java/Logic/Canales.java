/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import DB.DataBase;


public class Canales {

    DataBase db;

    public Canales() {
        db = new DataBase();
    }

    public JSONObject getCanales() throws SQLException {
        String sql = "SELECT id, nombre FROM canal";
        ResultSet rtst = db.execQuery(sql);

        JSONObject json = new JSONObject();
        while (rtst.next()) {
            json.put("id", rtst.getInt("id"));
            json.put("nombre", rtst.getString("nombre"));
        }

        return json;
    }

    public void setCanal() {

    }
}
