package com.jarexecutor.logic;

import com.jarexecutor.executor.Executor;
import com.jarexecutor.executor.Saver;
import com.jarexecutor.models.Module;
import com.jarexecutor.parser.XMLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainLogic {

    public void process(String[] args){
        String fromPath = null;
        String toPath = null;
        String profile = null;

        for (String string: args) {
            if(string.contains("FromPath="))
                fromPath = string.replace("FromPath=", "");
            if(string.contains("ToPath="))
                toPath = string.replace("ToPath=", "");
            if(string.contains("Profile="))
                profile = string.replace("Profile=", "");
        }

        if(fromPath == null || toPath == null || profile == null)
            throw new IllegalArgumentException("One or more arguments are null" );

        XMLParser parser = new XMLParser();
        if(fromPath.charAt(fromPath.length() - 1) != '/')
            fromPath.concat("/");
        if(toPath.charAt(toPath.length() - 1) != '/')
            toPath.concat("/");

        List<String> map = parser.parse(fromPath + "pom.xml");
        Saver saver = new Saver(toPath.replace("?", ""));
        Executor executor = new Executor(fromPath);
        System.out.println("Cleaning");
        saver.clean();
        Module module = executor.execute(map);
        saver.save(module);
    }
}
