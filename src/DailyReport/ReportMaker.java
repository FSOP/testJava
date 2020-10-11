package DailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;

public class ReportMaker implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        merge_with_region();

    }

    public void merge_without_region(){
        Document document = new Document();

        document.loadFromFile("Test template.docx");
        String[] fieldNames = new String[]{"numApple", "numPear"};
        String[] fieldValues = new String[]{"123", "321"};

        try {
            document.getMailMerge().execute(fieldNames, fieldValues);
            document.saveToFile("MailMerge.docx", FileFormat.Docx_2013);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void merge_with_region(){

        Document document = new Document();
        document.loadFromFile("Test template.docx");
        try {
            document.getMailMerge().executeWidthRegion("testData.xml");

            document.saveToFile("Merged File.docx", FileFormat.Docx_2013);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void merge_nested_region(){

        //create a Document instance
        Document document = new Document();
        //load the template document
        document.loadFromFile("nestedMailMergeTemplate.docx");

        List list = new ArrayList();
        Map<String, String> dictionaryEntry = new HashMap<>();
        dictionaryEntry.put("Customer", "");
        list.add(dictionaryEntry.entrySet().iterator().next());

        dictionaryEntry = new HashMap<>();
        dictionaryEntry.put("Order", "Customer_Id = %Customer.Customer_Id%");
        list.add(dictionaryEntry.entrySet().iterator().next());

        //call executeWidthNestedRegion method to execute mail merge with nested region
        try {
            document.getMailMerge().executeWidthNestedRegion("orders.xml", list);
            document.saveToFile("nestedMailMerge.docx", FileFormat.Docx_2013);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //save the document

    }
}

