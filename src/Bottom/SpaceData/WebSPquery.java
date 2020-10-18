package Bottom.SpaceData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import java.util.HashMap;


public class WebSPquery extends WebAuthSP {
    HashMap<String, String> conf = new HashMap<String, String>();
    /*

     */
    public WebSPquery(HashMap<String, String> confi){
        conf = confi;
    }

    /*

     */
    public String spquery(String query){
        HttpsURLConnection conn = this.SPAuth(conf);
        String lastline = "";
        String out;

        try {
            URL url = new URL(query);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));


            while ((out = br.readLine()) != null){
                lastline = out;
            }

            //Logout and Close Connction
            new URL("https://www.space-track.org/ajaxauth/logout").openStream();
            conn.disconnect();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lastline;

    }
    public static void main(String args[]) throws UnsupportedEncodingException {

    }
}
