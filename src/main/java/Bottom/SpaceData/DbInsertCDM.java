package Bottom.SpaceData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Bottom.DbBottom;
import Bottom.JsonHandler;
import Bottom.SpaceData.CDMstructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;

public class DbInsertCDM extends DbBottom {
    public void insertCDM(String str_cdms, HashMap<String, String> conf){
        int lenCDM;
        initDB(conf.get("currentdata"));

        queryMaker(new CDMstructure().CDMstruct(conf.get("CDMstruct")),
                new JsonHandler().jsonhanle(str_cdms), conf.get("currentdata"));

    }

    public void queryMaker(HashMap<String, String> cdmstruct, JSONArray arrayCDM, String db_loc){
        initDB(db_loc);
        String query = "insert into cdm (";
        String querytail = "";

        for (String tag: cdmstruct.keySet()){
            query += tag +",";
            querytail += "?,";
        }
        querytail = querytail.substring(0, querytail.length()-1);
        query = query.substring(0, query.length()-1)+") values (" + querytail + ")";

        try {

            PreparedStatement pstmt = conn.prepareStatement(query);

            for (Object tcdm : arrayCDM){
                JSONObject cdm = (JSONObject) tcdm;
                int i = 1;

                for (String tag: cdmstruct.keySet()){
                    pstmt.setString(i, (String) cdm.get(cdmstruct.get(tag)));
                    i++;
                }
                try {
                    pstmt.executeUpdate();
                } catch (SQLException ee){
                    pstmt.clearParameters();
                    System.out.println(ee.getMessage());
                }
            }


        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }





    }
}
