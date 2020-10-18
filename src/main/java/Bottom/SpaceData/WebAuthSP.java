package Bottom.SpaceData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class WebAuthSP implements InterfaceSP {
    public HttpsURLConnection SPAuth(HashMap<String, String> conf){
        String baseURL = InterfaceSP.baseSPurl;
        String authPath = InterfaceSP.SPauthurl;
        String userName = conf.get("SPID");
        String password = conf.get("SPPW");
        String input = String.format(InterfaceSP.SPauthformat, userName, password);
        String query;

        HttpsURLConnection conn = null;
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);


        try {
            URL url = new URL(baseURL + authPath);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String output;
            System.out.println("login test: "+input);

            OutputStream os = conn.getOutputStream();
            System.out.println("input getbyte : "+input.getBytes());
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((output = br.readLine()) != null){
                //System.out.println(output);
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
