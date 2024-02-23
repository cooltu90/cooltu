package com.codingtu.cooltu.path;

import com.codingtu.cooltu.path.defaultvalue.GradeDefault;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.PathList;
import com.codingtu.cooltu.processor.annotation.path.PathObtain;
import com.codingtu.cooltu.processor.annotation.path.Paths;

import java.io.File;

@Paths(name = "document", path = "AppData/document/{grade}/{className}")
public class DocumentPathConfigs {

    @PathObtain(value = {GradeDefault.class}, name = "obtain")
    String obtain;

    @DirPath
    String infos;

    @DirPath(dirName = "物品", fieldName = "classGoods")
    String goods;

    @DirPath(parent = "goods", dirName = "info")
    String goodsInfos;

    @DirPath
    String students;

    @DirPath(parent = "students", dirName = "{student}", list = true)
    String student;

    @PathList("student")
    public boolean studentFilter(File file, String name) {
        return true;
    }


}
