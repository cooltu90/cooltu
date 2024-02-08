package com.codingtu.cooltu.bean;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.form.bind.BindView;
import com.codingtu.cooltu.processor.annotation.form.bind.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.bind.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.bind.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.check.Check;
import com.codingtu.cooltu.processor.annotation.form.check.CheckMethod;
import com.codingtu.cooltu.processor.annotation.form.check.CheckType;
import com.codingtu.cooltu.processor.annotation.form.link.LinkMethod;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.link.LinkView;

@FormBean
public class Photo extends CoreBean {

    @BindView(id = R.id.schoolEt, push = DefaultEditTextPush.class)
    @Check(prompt = "请输入学校", type = CheckType.METHOD)
    public String school;

    @BindEditText(R.id.labelEt)
    @Check(prompt = "请输入标签", type = CheckType.METHOD)
    public String label;

    @BindEditText(R.id.name1Et)
    @Check(prompt = "请输入name1", type = CheckType.METHOD)
    public String name1;

    @BindEditText(R.id.name2Et)
    @Check(prompt = "请输入name2")
    public String name2;

    @BindEditText(R.id.classEt)
    @Check(prompt = "请输入班级名")
    public String className;

    @BindRadioGroup(id = R.id.classLl, onSetItem = TypeOnSetItem.class)
    @Check(prompt = "请选择班级类型")
    public int classType;

    @BindRadioGroup(id = R.id.numberLl, onSetItem = TypeOnSetItem.class)
    public int numbers = -1;

    @BindEditText(R.id.otherEt)
    @Check(prompt = "请输入数字")
    public String others;

    @BindSeekBar(R.id.timeSb)
    public int time;

    @LinkMethod(R.id.name1Et)
    public void dealName1(@LinkView(R.id.name2Et) EditText name2Et) {
        ViewTool.setEditTextAndSelection(name2Et, name1);
    }

    @LinkMethod(R.id.classLl)
    public void dealClass(@LinkView(R.id.classEt) EditText classEt) {
        ViewTool.visibleOrGone(classEt, classType == 1);
    }


    @LinkMethod(R.id.otherEt)
    public void dealOthers(
            @LinkView(R.id.numberLl) LinearLayout numberLl,
            @LinkView(R.id.otherBt) TextView otherBt) {
        others = standardIndex(others);
        int index = getIndex(others);
        RadioGroup rg = (RadioGroup) numberLl.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0);
        rg.setSelected(index);
        if (index == -1 && StringTool.isNotBlank(others)) {
            otherBt.setTextColor(Color.RED);
            ViewTool.setText(otherBt, others);
        } else {
            otherBt.setTextColor(Color.BLACK);
            ViewTool.setText(otherBt, "其他");
        }
    }

    private int getIndex(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            if (index <= 3 && index >= 1) {
                return index - 1;
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public String standardIndex(String indexStr) {
        float index = -1;
        try {
            index = Float.parseFloat(indexStr);
        } catch (Exception e) {
            return null;
        }
        if (index > 0 && index < 100) {
            indexStr = floatToStr(index);
            return indexStr;
        }
        return null;
    }

    public String floatToStr(float floorFloat) {
        String str = floorFloat + "";
        if (str.endsWith(".0")) {
            return str.substring(0, str.length() - 2);
        }

        int i = str.indexOf(".");
        String zheng = str.substring(0, i);
        String xiao = str.substring(i);
        int z = Integer.parseInt(zheng);
        float v = Float.parseFloat(xiao);
        if (v > 0) {
            return z + ".5";
        } else {
            return z + "";
        }
    }

    @LinkMethod(R.id.numberLl)
    public void dealNumbers(@LinkView(R.id.otherEt) EditText otherEt) {
        Logs.i("numbers:" + numbers);
        if (numbers >= 0) {
            ViewTool.setEditTextAndSelection(otherEt, numbers + 1);
        }
    }

    @CheckMethod({R.id.labelEt, R.id.name1Et, R.id.schoolEt})
    public boolean checkLabel(String label) {
        if (StringTool.isBlank(label)) {
            return false;
        }
        return true;
    }


}
