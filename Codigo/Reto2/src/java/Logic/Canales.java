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
import org.json.simple.JSONArray;

public class Canales {

    DataBase db;

    public Canales() throws ClassNotFoundException {
        db = new DataBase();
    }

    public JSONArray getCanales() throws SQLException {
        String sql = "SELECT id, nombre FROM canal";
        ResultSet rtst = db.execQuery(sql);

        JSONArray jsonArr = new JSONArray();
        /*JSONObject json = new JSONObject();
s
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("nombre", "yisus");
        jsonObj.put("id", 1);

        JSONObject jsonObj2 = new JSONObject();

        jsonObj2.put("nombre", "craist");
        jsonObj2.put("id", 2);
        jsonArr.add(jsonObj);
        jsonArr.add(jsonObj2);*/

        while (rtst.next()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", rtst.getInt("id"));
            jsonObj.put("nombre", rtst.getString("nombre"));
            jsonArr.add(jsonObj);
        }

        return jsonArr;
    }

    public void setCanal() {

    }
}
