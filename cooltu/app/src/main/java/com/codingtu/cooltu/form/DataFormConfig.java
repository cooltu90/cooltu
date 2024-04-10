package com.codingtu.cooltu.form;

import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormDatas;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.forms.FormConfig;
import com.codingtu.cooltu.processor.annotation.forms.link.Link;
import com.codingtu.cooltu.processor.annotation.forms.link.Links;
import com.codingtu.cooltu.processor.annotation.forms.check.CheckField;
import com.codingtu.cooltu.processor.annotation.forms.check.Checks;
import com.codingtu.cooltu.processor.annotation.forms.echo.Echo;
import com.codingtu.cooltu.processor.annotation.forms.echo.NoEcho;
import com.codingtu.cooltu.processor.annotation.forms.radiogroup.FormRadioGroupGetViews;
import com.codingtu.cooltu.processor.annotation.forms.radiogroup.FormRadioGroupItems;
import com.codingtu.cooltu.processor.annotation.forms.view.FormEditText;
import com.codingtu.cooltu.processor.annotation.forms.view.FormRadioGroup;
import com.codingtu.cooltu.processor.annotation.tools.Name;

import retrofit2.http.QueryName;

@FormConfig(FormDatas.FormData.class)
public class DataFormConfig {

    @Echo(methodName = "echoNamexxx", ids = {R.id.nameEt, R.id.nicknameEt})
    @FormEditText(R.id.nameEt)
    @Links({
            @Link(methodName = "handleNamexxx", ids = {R.id.nameEt, R.id.nicknameEt}),
            @Link(methodName = "handleName1", ids = {R.id.nameEt, R.id.nicknameEt})
    })
    @Checks(
            @CheckField(methodName = "checkName", ids = {R.id.nameEt})
    )
    public String name;

    @Name("echoNamexxx")
    public void echoName(FormDatas.FormData formData, String name, EditText nameEt, EditText nicknameEt) {

    }

    @Name("checkName")
    public String checkName(FormDatas.FormData formData, EditText nameEt) {
        String name = nameEt.getText().toString();
        if (StringTool.isBlank(name)) {
            throw new RuntimeException("xxxx");
        }
        return name;
    }

    @Name("handleNamexxx")
    public void handleName(Message msg, EditText nameEt, EditText nicknameEt) {

    }

    public void handleName1(Message msg, EditText nameEt, EditText nicknameEt) {

    }

    @NoEcho
    @FormEditText(R.id.nicknameEt)
    @CheckField(prompt = "请输入昵称")
    public String nickname;

    @FormRadioGroup(id = R.id.numLl, onSetItem = TypeOnSetItem.class)
    @FormRadioGroupItems
    @FormRadioGroupGetViews("getNumViews")
    @Link(methodName = "handleNum", ids = R.id.numLl)
    @CheckField(prompt = "检测")
    public String num;

    @Name("getNumViews")
    public View[] getNumViews(LinearLayout numLl) {
        return ViewTool.getChildren(numLl);
    }

    public void handleNum(Message msg, LinearLayout numLl) {

    }

    public String checkNum(FormDatas.FormData formData, LinearLayout numLl, String prompt) {
        RadioGroup rg = ViewTool.getRadioGroup(numLl);
        String currentItem = rg.getCurrentItem();
        Logs.i("currentItem:" + currentItem);
        if (StringTool.isBlank(currentItem)) {
            throw new RuntimeException(prompt);
        }
        return currentItem;
    }

}
