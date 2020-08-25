package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class DOMDictionaryXML {

    private static boolean checkFileXMLExist(String filePathString) {
        File f = new File(filePathString);
        return f.exists() && !f.isDirectory();
    }

    public static HashMap<String, String> loadDictionaryFromFile(String fileName) throws IOException, SAXException, ParserConfigurationException {
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


    public static String addWordToDictionaryXML(String wordNeedToAdd, String meaningOfWordNeedToAdd, String fileName) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document;
        document = documentBuilder.parse(fileName);
        Element rootRecord = (Element) document.getElementsByTagName("dictionary").item(0);

        NodeList nodeList = document.getElementsByTagName("record");
        boolean checkExist = false;
        for(int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String word = element.getElementsByTagName("word").item(0).getTextContent();
                String meaning = element.getElementsByTagName("meaning").item(0).getTextContent();

                if (word.equals(wordNeedToAdd) && meaning.equals(meaningOfWordNeedToAdd)){
                    checkExist = true;
                    break;
                }
            }
        }
        if (checkExist){
           return "This word already exists.";
        } else {
            Element newRecord = document.createElement("record");
            rootRecord.appendChild(newRecord);
            Element word = document.createElement("word");
            word.appendChild(document.createTextNode(wordNeedToAdd));
            newRecord.appendChild(word);
            Element meaning = document.createElement("meaning");
            meaning.appendChild(document.createTextNode(meaningOfWordNeedToAdd));
            newRecord.appendChild(meaning);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
            return "Add word successful.";
        }
    }

    public static boolean removeWordFromXMLDictionary(String wordNeedToRemove, String fileName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document;
        document = documentBuilder.parse(fileName);
        Element rootRecord = (Element) document.getElementsByTagName("dictionary").item(0);

        boolean check = false;

        NodeList nodeList = document.getElementsByTagName("record");
        for(int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String word = element.getElementsByTagName("word").item(0).getTextContent();
                if (word.equals(wordNeedToRemove)){
                    element.removeChild(element.getElementsByTagName("word").item(0));
                    element.removeChild(element.getElementsByTagName("meaning").item(0));
                    rootRecord.removeChild(element);
                    check = true;
                }
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
        return check;
    }

    public static void saveDictionary(String fileName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document;
        document = documentBuilder.parse(fileName);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    public static void addWordToHistoryDictionaryXML(String word, String fileName) throws ParserConfigurationException, ParseException, IOException, SAXException, TransformerException {
        if (word == null || word == "" || word.length() == 0) {
            return;
        }

        Date now = new Date();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document;

        if (!checkFileXMLExist(fileName)) {
            document = documentBuilder.newDocument();
            // root element
            Element rootElement = document.createElement("history");
            document.appendChild(rootElement);
            // Date element
            Element date = document.createElement("date");
            rootElement.appendChild(date);
            Attr attribute = document.createAttribute("value");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(now);
            attribute.setValue(formattedDate);
            date.setAttributeNode(attribute);
            // word element
            Element wordElement = document.createElement("word");
            wordElement.appendChild(document.createTextNode(word));
            date.appendChild(wordElement);
        } else {
            File inputFile = new File(fileName);
            document = documentBuilder.parse(inputFile);

            NodeList dates = document.getElementsByTagName("date");

            boolean checkExist = false;

            for (int i = 0; i < dates.getLength(); i++) {
                Node nNode = dates.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateValue = parser.parse(eElement.getAttribute("value"));

                    LocalDate first = dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate second = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    if (first.compareTo(second) == 0) {
                        checkExist = true;

                        // word element
                        Element wordElement = document.createElement("word");
                        wordElement.appendChild(document.createTextNode(word));

                        nNode.appendChild(wordElement);
                    }
                }
            }

            if (!checkExist) {
                Node root = document.getElementsByTagName("history").item(0);

                // Date element
                Element date = document.createElement("date");
                root.appendChild(date);

                // setting attribute to date element
                Attr attribute = document.createAttribute("value");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(now);
                attribute.setValue(formattedDate);
                date.setAttributeNode(attribute);

                // word element
                Element wordElement = document.createElement("word");
                wordElement.appendChild(document.createTextNode(word));
                date.appendChild(wordElement);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    public static HashMap<String, Integer> getFrequentWordList(Date from, Date to, String fileName) throws ParserConfigurationException, IOException, SAXException, ParseException {
        HashMap<String, Integer> result = new HashMap<>();

        File xmlDictionaryFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlDictionaryFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("date");

        for(int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
                Date dateValue = parser.parse(element.getAttribute("value"));

                LocalDate first = dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate second = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate third = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (first.compareTo(second) >= 0 && first.compareTo(third) <= 0) {
                    NodeList insideList = element.getElementsByTagName("word");

                    for (int i = 0; i < insideList.getLength(); i++) {
                        Node insideNode = insideList.item(i);

                        if (insideNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element insideElement = (Element) insideNode;

                            String word = insideElement.getTextContent();
                            if (result.containsKey(word)) {
                                result.put(word, result.get(word) + 1);
                            } else {
                                result.put(word, 1);
                            }
                        }
                    }
                }

            }
        }
        return result;
    }
}
