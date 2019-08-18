package com.jarexecutor.app;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        Map<String, ArrayList<String>> map = new HashMap<>();

        File inputFile = new File("C:\\Users\\razil\\Desktop\\Git\\nextoria\\pom.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("modules");
        ArrayList<String> strings = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            strings.add(nList.item(temp).getTextContent());
//            NodeList nodeList = element.getElementsByTagName("module");
//            ArrayList<String> strings = new ArrayList<>();
//            for (int i = 0; i < nodeList.getLength(); i++)
//                strings.add(nodeList.item(i).getTextContent());
//            map.put(element.getElementsByTagName("id").item(0).getTextContent(), strings);
        }
        System.out.println(strings);
    }
}
