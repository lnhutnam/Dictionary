package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions;
// Import section
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class LoadDictionaryFromXML {
    public static HashMap<String, String> loadDictionaryFromFile(String fileName) throws IOException, SAXException, ParserConfigurationException{
        HashMap<String, String> result = new HashMap<String, String>();

        File xmlDictionaryFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlDictionaryFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("record");

        for(int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE){
               Element element = (Element) node;

               String word = element.getElementsByTagName("word").item(0).getTextContent();
               String meaning = element.getElementsByTagName("meaning").item(0).getTextContent();

               result.put(word, meaning);
            }
        }

        return result;
    }
}
