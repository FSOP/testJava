package Panels;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbBottom {
    Connection conn;
    Statement state;
    public void initDB(){
        init_read("jdbc:sqlite:data\\currentdata.txt?journal_mode=off");
    }

    public void initDB(String db_loc){

        init_read(db_loc);
    }

    public void init_read(String loc){
        try {

            conn = DriverManager.getConnection(loc);
            state = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
