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

        buttonReportMake.addActionListener(new DailyReport());

    }

    public HashMap get_panel_data(){
        HashMap<String, String> panel_data = new HashMap<>();

        panel_data.put("who_make",textField1.getText());
        panel_data.put("status_sensor1",comboBox1.getToolTipText());

        return panel_data;
    }

}
