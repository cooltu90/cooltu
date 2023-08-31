package com.codingtu.cooltu.processor.builder.builder;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.BuilderType;
import com.codingtu.cooltu.processor.builder.bean.ActBaseBean;
import com.codingtu.cooltu.processor.builder.builder.base.BaseBuilder;
import com.codingtu.cooltu.processor.builder.temp.ActBaseTemp;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.util.List;

@To(ActBaseTemp.class)
public class ActBaseBuilder extends BaseBuilder {
    public ActBaseBean actBaseBean;

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.actBase;
    }


    @Override
    public void create() {
        super.create();

        List<String> tempLines = getTempLines(ActBaseTemp.class);

        List<String> lines = Ts.ts(tempLines).convert(new BaseTs.Convert<String, String>() {
            @Override
            public String convert(int index, String s) {



                return s;
            }
        }).get();

        Logs.i(lines);

    }
}
