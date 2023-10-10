package com.codingtu.cooltu.path;

import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.Paths;

@Paths(name = "check", path = "EnvCheckData/tasks/{company}/{taskName}")
public class CheckPathConfigs {

    @DirPath
    String DeleteLabel;

}
