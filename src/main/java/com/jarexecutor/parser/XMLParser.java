package com.jarexecutor.parser;

import lombok.SneakyThrows;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class XMLParser {
    @SneakyThrows
    public List<String> parse(String path) {
        //Map<String, ArrayList<String>> map = new HashMap<>();

        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("modules");
        List<String> strings = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            strings.add(nList.item(temp).getTextContent());
        }

        return strings;
    }

}
