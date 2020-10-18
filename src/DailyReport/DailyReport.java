package DailyReport;

import Bottom.*;
import Bottom.ReportMaker.*;
import Panels.ReportPanel;
import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DailyReport extends XMLBottom implements ActionListener, InterfaceCriteria {

    HashMap<String, String> panel_data = new HashMap<>();
    ReportPanel panel;
    public DailyReport(JFrame frame){
        panel = (ReportPanel) frame;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        initXml();
        panel_data = panel.get_panel_data();

        Element root = document.createElement("DailyReport");

        root.appendChild(new DbXML_CDMReport().reportCDM_xml());
        root.appendChild(new DbReportCAT().reportCat());
        /*

         */
        System.out.println(panel_data.get("status_sensor1"));

        XMLBottom.createXML(root, report_xml_loc+"testReport.xml");


        System.out.println(Db_Json2XML.cdm_2down.size());
        if (Db_Json2XML.cdm_2down.size() != 0){  new Db_Json2XML().json2xml(Db_Json2XML.cdm_2down.get(0)); }
        else System.out.println("no cdm to download");

        new DocxMaker().merge_with_region();

        System.out.println("작업종료료");
   }

   public void get_panel_data(){

   }
}
