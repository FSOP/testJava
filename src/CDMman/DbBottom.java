package CDMman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DbBottom {
    static Connection conn = null;
    Statement state;
    PreparedStatement pstmt;
    String dbloc = "data/currentdata.txt?journal_mode=off";
    String dbURL = "jdbc:sqlite:" + dbloc;

    public void initDB(){
        try{
            conn = DriverManager.getConnection(dbURL);
            state = conn.createStatement();
            System.out.println("DB Connection Established");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void initDB(String loc){
        try{
            String dbloc = "jdbc:sqlite:"+loc+"?journal_mode=off";
            conn = DriverManager.getConnection(dbloc);
            state = conn.createStatement();
            System.out.println("DB Connection Established");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


}
