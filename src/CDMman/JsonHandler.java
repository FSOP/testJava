package CDMman;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
}
