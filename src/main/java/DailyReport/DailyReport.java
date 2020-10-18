package DailyReport;

import Bottom.ReportMaker.DbXML_CDMReport;
import Bottom.ReportMaker.Db_Json2XML;
import Bottom.XMLBottom;
import org.w3c.dom.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyReport extends XMLBottom implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        initXml();
        Element root = document.createElement("DailyReport");

        root.appendChild(new DbXML_CDMReport().reportCDM_xml());


        XMLBottom.createXML(root, "C:\\Users\\User\\IdeaProjects\\testReport.xml");


        System.out.println(Db_Json2XML.cdm_2down.get(0));
        new Db_Json2XML().json2xml(Db_Json2XML.cdm_2down.get(0));

    }
}
