import DailyReport.DailyReport;
import SpaceData.CDMdownload;
import Bottom.ReportMaker.DocxMaker;

import javax.swing.*;

public class testMainApp extends JFrame{
    private JPanel panel;
    private JButton button1;
    private JButton button2;
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

        button1.addActionListener(new Button1(this));
        button2.addActionListener(new button2(this));
        testJsonButton.addActionListener(new TestJson());
        testCDMdownloadButton.addActionListener(new CDMdownload());
        testReportMakerButton.addActionListener(new DailyReport());

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
