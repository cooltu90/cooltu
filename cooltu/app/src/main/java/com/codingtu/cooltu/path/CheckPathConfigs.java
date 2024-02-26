package com.codingtu.cooltu.path;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.PathBeanType;
import com.codingtu.cooltu.path.defaultvalue.CompanyDefault;
import com.codingtu.cooltu.path.filter.LabelFilter;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.PathList;
import com.codingtu.cooltu.processor.annotation.path.PathObtain;
import com.codingtu.cooltu.processor.annotation.path.Paths;

import java.io.File;

@Paths(name = "check", path = "EnvCheckData/tasks/{company}/{taskName}")
public class CheckPathConfigs {

//    @PathObtain
//    public void obtain(@DefaultPath(CompanyDefault.class) String company, String taskName) {
//    }

    @PathObtain(value = {CompanyDefault.class}, name = "obtain1")
    String obtain;

    @DirPath
    String DeleteLabel;

    @FilePath(
            parent = "DeleteLabel",
            fileName = "i4l",
            fieldName = "i4l",
            fileType = FileType.TXT
    )
    String DeleteLabelI4l;

    @FilePath(
            parent = "DeleteLabel",
            fileName = "user",
            fieldName = "user",
            fileType = FileType.TXT,
            beanClass = User.class)
    String DeleteLabelUser;

    @DirPath(parent = "DeleteLabel", dirName = "{labelName}", fieldName = "label")
    String DeleteLabelLabel;

    @FilePath(
            parent = "DeleteLabelLabel",
            fileName = "{labelName}",
            fieldName = "label",
            fileType = FileType.TXT,
            beanClass = User.class)
    String DeleteLabelLabelLabel;

    @DirPath
    String ExtraInfo;

    @DirPath(parent = "ExtraInfo", dirName = "DeleteLabel", fieldName = "DeleteLabel")
    String ExtraInfoDeleteLabel;

    @DirPath(parent = "ExtraInfoDeleteLabel", dirName = "{labelName}", fieldName = "label", list = true)
    String ExtraInfoDeleteLabelLabel;


    @DirPath(dirName = "{labelName}", list = true)
    String label;

    @PathList({"label", "ExtraInfoDeleteLabelLabel"})
    public boolean labelFilter(File file) {
        return file.getName().startsWith("L-");
    }

    @FilePath(
            parent = "label",
            fileName = "{labelName}",
            fieldName = "label",
            fileType = FileType.TXT,
            beanClass = User.class,
            list = true
    )
    String labelTxt;

    @FilePath(
            parent = "label",
            fileName = "handle",
            fieldName = "handle",
            fileType = FileType.JPG)
    String handleJpg;

    @FilePath(
            parent = "label",
            fileName = "handle.jpg",
            fieldName = "handle_jpg",
            fileType = FileType.PNC)
    String handleJpgPnc;


    @FilePath(
            fileName = "1.txt",
            fieldName = "list",
            fileType = FileType.TXT,
            fileContentType = FileContentType.TXT,
            beanClass = User.class,
            beanType = PathBeanType.LIST
    )
    String list;

}
