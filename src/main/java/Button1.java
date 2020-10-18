import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Button1 implements ActionListener {
    testMainApp superapp;
    public Button1(testMainApp app){
        superapp = app;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        superapp.setTestLabel("Test");
        System.out.println("test performed");

    }
    public static void main(String args[]){
        //new testMainApp();
    }
}
