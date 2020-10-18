package Bottom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DbBottom {
    public Connection conn = null;
    public Statement state;
    PreparedStatement pstmt;
    String dbloc = "C:\\Users\\ukouu\\IdeaProjects\\currentdata.txt";
    String dbURL = "jdbc:sqlite:" + dbloc +"?journal_mode=off";

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

    public void closeConn(Connection conn){
        try {
            state.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Statement getStatement(){
        return state;
    }


}
