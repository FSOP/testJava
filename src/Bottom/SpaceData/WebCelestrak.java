package Bottom.SpaceData;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WebCelestrak {
    URL url;
    public BufferedReader downCelestrak() {
        BufferedReader br = null;
        try {

            url = new URL("http://celestrak.com/pub/satcat.txt");
            URLConnection conn = url.openConnection();

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            br = new BufferedReader(reader);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return br;
    }

    public void closeCon(){

    }


    public static void main(String args[]){

    }


}
