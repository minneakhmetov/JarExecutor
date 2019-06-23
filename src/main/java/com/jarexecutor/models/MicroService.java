package com.jarexecutor.models;

import lombok.*;

import java.io.File;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MicroService {
    String name;
    File jar;
    List<File> dockerFiles;
}
