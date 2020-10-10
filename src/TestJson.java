import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Set;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestJson implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        BufferedReader data = getFileBuffer();
        JSONParser parser = new JSONParser();
        try {
            //System.out.println(data.readLine());
            JSONArray jarray = (JSONArray) parser.parse(data.readLine());
            JSONObject obj = (JSONObject) jarray.get(1);
            System.out.println("Number of CDMs: "+jarray.size());
            System.out.println("Number of Objects : "+obj.size());
            Set key = obj.keySet();

            Iterator<String> iter = key.iterator();
            int i = 1;
            while(iter.hasNext()){
                String keyname = iter.next();
                //System.out.println(i + " key: " + keyname +" value: "+obj.get(keyname));
                System.out.println(keyname + " ## " + obj.get(keyname));
                i++;
            }

        } catch (IOException ee){
            System.out.println(ee.getMessage());
        } catch (ParseException eee){

        }
    }

    public BufferedReader getFileBuffer(){
        JFileChooser fc = new JFileChooser("C:\\Users\\ukouu\\IdeaProjects");
        BufferedReader br = null;
        JPanel panel = new JPanel();

        try {

            fc.showOpenDialog(panel);
            br = new BufferedReader(new FileReader(fc.getSelectedFile().getAbsoluteFile()));

        } catch (IOException ee){
            System.out.println(ee.getMessage());
        }
        return br;
    }
}
