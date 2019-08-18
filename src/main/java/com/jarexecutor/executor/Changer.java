package com.jarexecutor.executor;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

public class Changer {

    @SneakyThrows
    public String editDockerFile(File dockerfile){
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(dockerfile);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            stringBuilder.append(line.replace("/target/", ""));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
