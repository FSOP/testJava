package Bottom.ReportMaker;

import Bottom.XMLBottom;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbReportCAT extends XMLBottom {
        String[] nations = {"US", "SKOR"};

    public Element reportCat(){

        JSONObject diff_cat = new JSONObject();
        JSONObject current_cat = get_current_cat();
        JSONObject yesterday_cat = get_yesterday_cat();
        //cat_summary 업데이트
        update_cat_summary(current_cat.toJSONString());

        for (Object obj: current_cat.keySet()){
            String nat = (String) obj;
            int dif = -1;
            if (yesterday_cat.get(nat) != null)  dif = Integer.parseInt(current_cat.get(nat).toString()) - Integer.parseInt(yesterday_cat.get(nat).toString());
            else dif = (int) current_cat.get(nat);

            diff_cat.put(nat, dif);
        }


        Element satcat = document.createElement("SATCAT");

        for (Object tkey :current_cat.keySet()){
            String key = (String) tkey;

            Element tmp = document.createElement(key);
            Element tmp_diff = document.createElement(key+"_diff");

            tmp.setTextContent(current_cat.get(key).toString());
            tmp_diff.setTextContent(diff_cat.get(key).toString());

            satcat.appendChild(tmp);
            satcat.appendChild(tmp_diff);
        }
        closeConn(conn);


        return satcat;
    }


    public JSONObject get_yesterday_cat(){
        initDB();
        JSONObject jobj = new JSONObject();
        JSONParser parser = new JSONParser();
        String query_yesterday = "select insertdate, summary_data from cat_summary order by insertdate desc limit 1";

        ResultSet res = null;
        try {
            res = state.executeQuery(query_yesterday);
            String val = res.getString(2);

            jobj = (JSONObject) parser.parse(val);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        closeConn(conn);
        return jobj;
    }

    public JSONObject get_current_cat(){
        JSONObject current_cat = new JSONObject();
        String query = "select ownership, count(*) from satcat group by ownership";
        ResultSet res = null;

        initDB();
        try {
            res = state.executeQuery(query);
            while(res.next()){

                current_cat.put(res.getString(1), res.getInt(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConn(conn);
        return current_cat;
    }


    public void update_cat_summary(String data){
        initDB();
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try {
            PreparedStatement pstmt = conn.prepareStatement("insert into cat_summary (insertdate, summary_data) values (?, ?)");
            pstmt.setString(1, now);
            pstmt.setString(2, data);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConn(conn);
    }

    public static void main(String args[]){
        new DbReportCAT().reportCat();
    }
 }
