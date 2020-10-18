package Panels;

import DailyReport.DailyReport;

import javax.swing.*;
import java.util.HashMap;

public class ReportPanel extends  JFrame{

    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JPanel panel2;
    private JButton buttonReportMake;

    public ReportPanel(){

    }

    public void showPanel(){
        setContentPane(this.panel2);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        pack();
        setTitle("ReportMaker");

        DailyReport dailyreport = new DailyReport(this);
        buttonReportMake.addActionListener(dailyreport);
    }

    public HashMap get_panel_data(){
        HashMap<String, String> panel_data = new HashMap<>();

        panel_data.put("who_make",textField1.getText());
        panel_data.put("status_sensor1",(String) comboBox1.getSelectedItem());

        return panel_data;
    }

}
