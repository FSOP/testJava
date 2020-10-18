package Panels;

import SpaceData.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testMainApp extends JFrame{
    private JPanel panel;
    private JButton button1;
    private JButton buttonCelestrakCAT;
    JLabel testLabel;
    private JButton testJsonButton;
    private JButton testCDMdownloadButton;
    private JButton testReportMakerButton;
    static testMainApp app;


    public testMainApp(){
        setContentPane(this.panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setTitle("test APp");
        System.out.println("loopin?");

        ReportPanel reportpanel = new ReportPanel();

        button1.addActionListener(new Button1(this));
        buttonCelestrakCAT.addActionListener(new SATCATdownload());
        testJsonButton.addActionListener(new TestJson());
        testCDMdownloadButton.addActionListener(new CDMdownload());
        //testReportMakerButton.addActionListener(new DailyReport());
        testReportMakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportpanel.showPanel();
            }
        });


    }

    public void setTestLabel(String testLabel) {
        this.testLabel.setText(testLabel);
    }

    public static void main(String args[]){

        new testMainApp();
//        app = new testMainApp();
//
//        app.setContentPane(app.panel);
//        app.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        app.setVisible(true);
//        app.pack();
//        app.setTitle("Test App");
    }
}

