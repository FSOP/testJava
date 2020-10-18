package DailyReport;

import Bottom.*;
import Bottom.ReportMaker.*;
import org.w3c.dom.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DailyReport extends XMLBottom implements ActionListener, InterfaceCriteria {

    public DailyReport(){
    }

    @Override
    public void actionPerformed(ActionEvent e){
        initXml();
        Element root = document.createElement("DailyReport");

        root.appendChild(new DbXML_CDMReport().reportCDM_xml());
        root.appendChild(new DbReportCAT().reportCat());


        XMLBottom.createXML(root, report_xml_loc+"testReport.xml");


        System.out.println(Db_Json2XML.cdm_2down.size());
        if (Db_Json2XML.cdm_2down.size() != 0){  new Db_Json2XML().json2xml(Db_Json2XML.cdm_2down.get(0)); }
        else System.out.println("no cdm to download");

        new DocxMaker().merge_with_region();

        System.out.println("작업종료료");

   }
}
