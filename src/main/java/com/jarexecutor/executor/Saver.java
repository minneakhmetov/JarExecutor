package com.jarexecutor.executor;

import com.jarexecutor.models.MicroService;
import com.jarexecutor.models.Module;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
public class Saver {

    private String path;

    public Changer changer = new Changer();

    public Saver(String path) {
        this.path = path;
    }

    @SneakyThrows
    public void save(Module module){
        //editDockerCompose(module.getDockerCompose());
        for (File dockerCompose : module.getDockerCompose())
            FileUtils.copyFile(dockerCompose, new File(path + dockerCompose.getName()));
        for (MicroService microService : module.getMicroServiceList()){
            if(microService.getJar() != null & microService.getDockerFiles().size() != 0) {
                File service = new File(path + "/" + microService.getName());
                service.mkdir();
                FileUtils.copyFile(microService.getJar(), new File(path + "/" + microService.getName() + "/" + microService.getJar().getName()));
                for (File dockerFile : microService.getDockerFiles())
                    if (dockerFile.getName().contains("Dockerfile"))
                        editDockerFile(dockerFile, microService.getName());
                System.out.println("Saving " + microService.getName());
            }
        }
    }

    @SneakyThrows
    private void editDockerCompose(File dockerFile){
        FileWriter writer = new FileWriter(path + "/" + dockerFile.getName(), false);
        writer.write(changer.editDockerFileToProduction(dockerFile));
        writer.flush();
    }

    @SneakyThrows
    private void editDockerFile(File dockerFile, String dir){
        FileWriter writer = new FileWriter(path + "/" + dir + "/" + dockerFile.getName(), false);
        writer.write(changer.editDockerFile(dockerFile));
        writer.flush();
    }

    @SneakyThrows
    public void clean(){
        FileUtils.cleanDirectory(new File(path));
    }
}



