package Bottom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class XMLBottom extends DbBottom {
    static public Document document;
    static DocumentBuilderFactory factory;
    static DocumentBuilder documentBuilder;
    static Element root;


    public static void initXml(){
        try {

            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            //root = document.createElement("DailyReport");


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void newElement(Element element_child){
        root.appendChild(element_child);
    }

    public static void createXML(String xml_loc){
        try {
            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            //ByteArrayOutputStream out = new ByteArrayOutputStream();
            StreamResult out = new StreamResult(new FileOutputStream(new File(xml_loc)));

//            DOMSource source = new DOMSource(document);
            StreamResult result = out;

            transformer.transform(new DOMSource(document), result);

            //System.out.println(new String(out.toByteArray(), StandardCharsets.UTF_8));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void createXML(Element root, String xml_loc){
        try {
            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            //ByteArrayOutputStream out = new ByteArrayOutputStream();
            StreamResult out = new StreamResult(new FileOutputStream(new File(xml_loc)));

//            DOMSource source = new DOMSource(document);
            StreamResult result = out;

            transformer.transform(new DOMSource(document), result);

            //System.out.println(new String(out.toByteArray(), StandardCharsets.UTF_8));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

}
