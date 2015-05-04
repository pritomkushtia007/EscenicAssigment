package com.assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.assignment.Json.FileOperation;
import com.assignment.Json.JSonPerser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Assignment {

    public static JSonPerser mJSonPerser;
    public static Map<String, String> mConfigurationMap;

    public static void main(String[] args) throws IOException {

        ReadFromConfigureFile();

        /*HERE IS THE WORKING FUNCTIONALITY OF 3 PHASES*/
        
//        Phase1Working(args[0]);
//        Phase2Working();
//        Phase3Working();

    }

    /**
     * Read configuration of a file
     * 
     * @return void
     */
    public static void ReadFromConfigureFile() {
        mConfigurationMap = new HashMap<String, String>();
        try {
            File file = new File("book-info-converter.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String sKey = (String) enuKeys.nextElement();
                String sValue = properties.getProperty(sKey);
                mConfigurationMap.put(sKey, sValue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phase 1 working function
     *
     * @param str
     *            argument passed by command line
     * @return void
     */
    public static void Phase1Working(String str) {
        boolean bToText = true;
        mJSonPerser = new JSonPerser();
        FileOperation.GetInstance().ReadFromFile(str);
        for (int k = 0; k < 1; k++) {
            if (FileOperation.GetFileData().get(k).contentEquals("{"))
                bToText = true;
            else
                bToText = false;
        }
        if (bToText)
            mJSonPerser.JsonToText();
        else
            mJSonPerser.TextToJSon();

        FileOperation.GetInstance().WriteToFile(mJSonPerser.GetFinalOutput(),
                "output.txt");
    }

    /**
     * Phase 2 working function
     * 
     * @return void
     */
    public static void Phase2Working() {

        mJSonPerser = new JSonPerser();
        FileOperation.GetInstance().ReadFromFile("inputjson.txt");

        if (mConfigurationMap.get("targetFormat").equalsIgnoreCase("txt"))
            mJSonPerser.JsonToText();
        else
            mJSonPerser.TextToJSon();
        if (!mJSonPerser.GetJsonMap().containsKey("isbn")
                && !mJSonPerser.GetJsonMap().containsKey("ISBN")) {
            System.out.println("conversion failed There is no ISBN");
        } else {
            if (mConfigurationMap.get("storageEnabled")
                    .equalsIgnoreCase("true"))
                FileOperation.GetInstance().WriteToFile(
                        mJSonPerser.GetFinalOutput(),
                        mConfigurationMap.get("storageFile"));
            System.out.println("Book Name :"
                    + mJSonPerser.GetJsonMap().get("name").toString()
                            .toUpperCase().replaceAll("[\\[\\],]", ""));
        }
    }

    /**
     * Phase 3 working function
     * 
     * @return void
     */
    public static void Phase3Working() {

        String sAuthors = new String();
        mJSonPerser = new JSonPerser();
        FileOperation.GetInstance().ReadFromFile("inputjson.txt");

        if (mConfigurationMap.get("targetFormat").equalsIgnoreCase("txt"))
            mJSonPerser.JsonToText();
        else
            mJSonPerser.TextToJSon();

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentFactory
                    .newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("book");
            document.appendChild(root);
            Attr attr = document.createAttribute("xmlns:b");
            attr.setValue("http://example.com/programming/test/book");
            root.setAttributeNode(attr);

            for (Map.Entry<String, List<String>> entry : mJSonPerser
                    .GetJsonMap().entrySet()) {
                String key = entry.getKey();
                for (String val : entry.getValue()) {

                    if (key.contains("authors")) {
                        sAuthors = sAuthors + val;
                    } else {
                    }
                }
            }

            Element isbn = document.createElement("isbn");
            isbn.appendChild(document.createTextNode(mJSonPerser.GetJsonMap()
                    .get("ISBN").toString().replaceAll("[\\[\\],]", "")));
            root.appendChild(isbn);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(mJSonPerser.GetJsonMap()
                    .get("name").toString().replaceAll("[\\[\\],]", "")));
            root.appendChild(name);

            Element authors = document.createElement("authors");
            root.appendChild(authors);
            ArrayList<String> aList = new ArrayList<String>(
                    Arrays.asList(sAuthors.split(",")));
            for (int i = 0; i < aList.size(); i++) {
                Element author = document.createElement("author");
                author.appendChild(document.createTextNode(aList.get(i)
                        .toString()));
                authors.appendChild(author);
            }

            Element publishedDate = document.createElement("published-date");
            publishedDate.appendChild(document.createTextNode(mJSonPerser
                    .GetJsonMap().get("published date").toString()
                    .replaceAll("[\\[\\],]", "")));
            root.appendChild(publishedDate);

            // create the xml file
            // transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(
                    "book-info.xml"));

            // If you use
            StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

}