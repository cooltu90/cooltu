package com.codingtu.cooltu.path;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.path.filter.LabelFilter;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.Paths;

@Paths(name = "check", path = "EnvCheckData/tasks/{company}/{taskName}")
public class CheckPathConfigs {

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

    @DirPath
    String ExtraInfo;

    @DirPath(parent = "ExtraInfo", dirName = "DeleteLabel", fieldName = "DeleteLabel")
    String ExtraInfoDeleteLabel;

    @DirPath(parent = "ExtraInfoDeleteLabel", dirName = "{labelName}", fieldName = "label", filter = LabelFilter.class)
    String ExtraInfoDeleteLabelLabel;


    @DirPath(dirName = "{labelName}", filter = LabelFilter.class)
    String label;

}
