package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.deal.FileLineDealer;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.read.ReadLine;
import com.codingtu.cooltu.processor.annotation.delete.DeleteAct;
import com.codingtu.cooltu.processor.builder.impl.ActResBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActStartBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class DeleteActDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        DeleteAct deleteAct = te.getAnnotation(DeleteAct.class);
        String name = deleteAct.name();
        String packages = deleteAct.packages();
        JavaInfo actJavaInfo = CurrentPath.act(packages, name);

        //删除AndroidManifest.xml
        //删除ActStart
        JavaInfo actStartJavaInfo = CurrentPath.javaInfo(FullName.ACT_START);

        FileReader.from(actStartJavaInfo.path).readLine(new ReadLine<String>() {
            private boolean isStart;

            @Override
            public void readLine(String line) {

                //                public static final void testDelete1Activity(Activity act) {
//                    Intent intent = new Intent(act, com.codingtu.cooltu.ui.TestDelete1Activity.class);
//                    intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
//                    com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.TEST_DELETE1_ACTIVITY);
//                }
                String trimLine = line.trim();
                if (trimLine.startsWith("public static final void testDelete1Activity")) {
                    isStart = true;
                } else if (isStart) {
                    
                }
            }
        });

        //删除layout
        String layoutName = "activity_" + name;
        File layoutFile = new File(CurrentPath.layout(layoutName));
        Logs.i("delete act:" + layoutFile.getAbsolutePath());
        //ActRes
        JavaInfo actResJavaInfo = CurrentPath.actRes(packages, name);
        Logs.i("delete act:" + actResJavaInfo.path);
        //ActBase
        JavaInfo actBaseJavaInfo = CurrentPath.actBase(actJavaInfo.fullName);
        Logs.i("delete act:" + actBaseJavaInfo.path);
        //Act
        Logs.i("delete act:" + actJavaInfo.path);

    }
}
