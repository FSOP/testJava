package SpaceData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import Bottom.*;
import Bottom.SpaceData.DbInsertSATCAT;
import Bottom.SpaceData.WebCelestrak;


public class SATCATdownload implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedReader br = new WebCelestrak().downCelestrak();

        new DbInsertSATCAT().insertSATCAT(br);
        //insertdb(br);

    }

    public void insertdb(BufferedReader brSAT){



    }


    public static void main(String args[]){

    }
}
