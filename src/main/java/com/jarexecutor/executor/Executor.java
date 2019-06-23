package com.jarexecutor.executor;

import com.jarexecutor.models.MicroService;
import com.jarexecutor.models.Module;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    private String path;

    public Executor(String path) {
        this.path = path;
    }

    public Module execute(List<String> strings){
        File file = new File(path);
        List<MicroService> microServices = new ArrayList<MicroService>();
        List<File> dockerComposes = new ArrayList<File>();
        for(String dirname: strings) {
            for (File candidate : file.listFiles()) {
                if (candidate.isDirectory() && candidate.getName().equals(dirname) && !candidate.getName().contains(".")) {
                    File[] targetAndSrc = candidate.listFiles();
                    MicroService microService = new MicroService();
                    List<File> dockerFiles = new ArrayList<File>();
                    microService.setName(candidate.getName());
                    for (File targetAndSrcCandidate : targetAndSrc) {
                        if (targetAndSrcCandidate.getName().contains("Dockerfile"))
                            dockerFiles.add(targetAndSrcCandidate);
                        if (targetAndSrcCandidate.getName().contains("target")) {
                            File[] inTarget = targetAndSrcCandidate.listFiles();
                            for (File inTargetCandidate : inTarget) {
                                if (inTargetCandidate.getName().contains(".jar") & !inTargetCandidate.getName().contains(".jar.original"))
                                    microService.setJar(inTargetCandidate);
                            }
                        }
                    }
                    microService.setDockerFiles(dockerFiles);
                    microServices.add(microService);
                    System.out.println("Executing " + microService.getName());
                }
                if (candidate.getName().contains("docker-compose"))
                    dockerComposes.add(candidate);
            }
        }
        return Module.builder()
                .microServiceList(microServices)
                .dockerCompose(dockerComposes)
                .build();

    }


}
