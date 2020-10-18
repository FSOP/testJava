package Bottom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonHandler {
    public JSONArray jsonhanle(String data){
        JSONParser parser = new JSONParser();
        JSONArray jarray = null;
        try{
            jarray = (JSONArray) parser.parse(data);
            for (Object obj: jarray){
                JSONObject cdm = (JSONObject) obj;
                //System.out.println("CDM: "+cdm.toString());
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return jarray;
    }

    public static void main(String args[]){
        try {

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\cdm_json.txt"));
            JSONArray j_array = new JsonHandler().jsonhanle(br.readLine());

            for(Object obj : j_array){
                JSONObject j_obj = (JSONObject) obj;
                for (Object key: j_obj.keySet()){
                    String skey = (String) key;
                    if (skey.endsWith("UNIT")) continue;

                    System.out.println(skey + " = " +skey);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
