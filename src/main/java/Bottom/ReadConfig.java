package Bottom;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;
import javax.swing.*;


public class ReadConfig {
    public HashMap<String, String> read(){
        BufferedReader br;
        JPanel panel = new JPanel();
        JFileChooser jc = new JFileChooser("C:\\Download\\");
        HashMap<String, String> config = new HashMap<String, String>();
        String ldata;

        jc.showOpenDialog(panel);
        try {
            br = new BufferedReader(new FileReader(jc.getSelectedFile().getAbsolutePath()));
            while((ldata = br.readLine()) != null){
                if (ldata.substring(0,1).equals("$")) {
                    continue;
                }
                String[] data = ldata.split("=");
                config.put(data[0].strip(), data[1].strip());
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return config;
    }

    public HashMap<String, String> read(String loc){
        BufferedReader br =null;
        HashMap<String, String> conf = new HashMap<>();
        try{
            br = new BufferedReader(new FileReader(loc));
            String line;
            while ((line = br.readLine()) != null){
                if (line.subSequence(0,1) == "#") continue;
                String[] data = line.split("=");
                conf.put(data[0].strip(), data[1].strip());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return conf;

    }
    public static void main(String args[]){
        ReadConfig rc = new ReadConfig();
        HashMap<String, String> conf = new HashMap<String, String>();

        conf = rc.read();
        System.out.println(conf.get("UserID"));

    }
}
