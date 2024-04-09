package com.codingtu.cooltu.form;

import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormDatas;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.forms.FormConfig;
import com.codingtu.cooltu.processor.annotation.forms.link.Link;
import com.codingtu.cooltu.processor.annotation.forms.link.Links;
import com.codingtu.cooltu.processor.annotation.forms.check.CheckField;
import com.codingtu.cooltu.processor.annotation.forms.check.Checks;
import com.codingtu.cooltu.processor.annotation.forms.echo.Echo;
import com.codingtu.cooltu.processor.annotation.forms.echo.NoEcho;
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
        formData.name = nameEt.getText().toString();
        if (StringTool.isBlank(formData.name)) {
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
    @CheckField
    public String nickname;

    @FormRadioGroup(
            id = R.id.numLl,
            viewsMethod = "getNumViews",
            onSetItem = TypeOnSetItem.class)
    @Link(methodName = "handleNum", ids = R.id.numLl)
    @Echo(methodName = "echoNum", ids = {R.id.numLl})
    @CheckField(methodName = "checkNum", ids = R.id.numLl, prompt = "检测")
    public String num;

    @Name("getNumViews")
    public View[] getNumViews(LinearLayout numLl) {
        return null;
    }

    public void echoNum(FormDatas.FormData formData, String num, LinearLayout numLl) {

    }

    public void handleNum(Message msg, LinearLayout numLl) {

    }

    public String checkNum(FormDatas.FormData formData, LinearLayout numLl, String prompt) {
        return null;
    }

}
