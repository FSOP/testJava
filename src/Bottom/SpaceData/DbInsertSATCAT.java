package Bottom.SpaceData;

import Bottom.DbBottom;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbInsertSATCAT extends DbBottom {

    public void insertSATCAT(BufferedReader br){
        initDB();
        String line;
        try {
            state.executeUpdate("delete from satcat");
            PreparedStatement pstmt = conn.prepareStatement("insert into satcat values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            while ((line = br.readLine()) != null) {

                pstmt.setString(1, line.substring(0, 11).strip());   //Int' Designator
                pstmt.setString(2, line.substring(13, 18).strip());   //Norad
                pstmt.setString(3, line.substring(19, 20).strip());   //Multiple NameFlag
                pstmt.setString(4, line.substring(20, 21).strip());   //Payload Flag
                pstmt.setString(5, line.substring(21, 22).strip());    //Operational Status Code
                pstmt.setString(6, line.substring(23, 47).strip());   //Satellite Names
                pstmt.setString(7, line.substring(49, 54).strip());   //Ownership
                pstmt.setString(8, line.substring(56, 66));   //launch date
                pstmt.setString(9, line.substring(68, 73));   //launch site
                pstmt.setString(10, line.substring(75, 85));   //Decay date
                pstmt.setString(11, line.substring(87, 94).strip());  //Orbital Period
                pstmt.setString(12, line.substring(96, 101).strip()); //Inclination
                pstmt.setString(13, line.substring(103, 109));    //Apogee Altitude
                pstmt.setString(14, line.substring(111, 117));    //Perigee Altitude
                pstmt.setString(15, line.substring(119, 127));    //Radar Cross Section
                pstmt.setString(16, line.substring(129, 132));    //Orbital Status Code
                //celestrak.com/satcat/satcat-format.php

                pstmt.executeUpdate();
            }

        } catch (IOException e){
            e.getMessage();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
