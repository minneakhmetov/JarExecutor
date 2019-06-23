package com.jarexecutor.parser;

import lombok.SneakyThrows;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class XMLParser {
    @SneakyThrows
    public Map<String, ArrayList<String>> parse(String path) {
        Map<String, ArrayList<String>> map = new HashMap<>();

        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("profile");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Element element = (Element) nList.item(temp);
            NodeList nodeList = element.getElementsByTagName("module");
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++)
                strings.add(nodeList.item(i).getTextContent());
            map.put(element.getElementsByTagName("id").item(0).getTextContent(), strings);
        }
        return map;
    }

}
