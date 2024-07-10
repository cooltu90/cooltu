package com.codingtu.cooltu.ui;

import android.widget.TextView;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4j.data.value.ByteArrayValue;
import com.codingtu.cooltu.lib4j.data.value.FloatValue;
import com.codingtu.cooltu.lib4j.data.value.IntArrayValue;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.CoreTs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import java.util.Comparator;
import java.util.List;

import core.actbase.StepOneActivityBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        catAdapter.updateItems(Ts.ts("1", "2", "3", "sdfsd"));

        int childCount = tvLl.getChildCount();
        TextView[] tvs = new TextView[childCount];
        for (int i = 0; i < childCount; i++) {
            tvs[i] = (TextView) tvLl.getChildAt(i);
        }


        xxxx(tvs);


    }

    @Override
    protected void dogAdapterLoadMore(int page) {


    }

    public void xxxx(TextView[] tvs) {
        BaseTs<Float> floatTs = Ts.ts(tvs).convert(new Ts.Convert<TextView, Float>() {
                    @Override
                    public Float convert(int index, TextView gasTv) {
                        String gas = gasTv.getText().toString();
                        try {
                            float v = Float.parseFloat(gas);
                            return v == 0 ? null : v;
                        } catch (Exception e) {
                        }
                        return null;
                    }
                }).removeSameItem()
                .sort(new Comparator<Float>() {
                    @Override
                    public int compare(Float o1, Float o2) {
                        return o1.compareTo(o2);
                    }
                });

        int[] ints = Ts.ts(0x7D, 0x7B,
                        0x01, 0xF5, 0x01, 0xF3,
                        0x3D, 0x66,
                        0x00, (floatTs.count() * 4 + 2),
                        0x00, floatTs.count())
                .add(floatTs.convertList(new Ts.Convert<Float, List<Integer>>() {
                    @Override
                    public List<Integer> convert(int index, Float aFloat) {
                        return Ts.ints(FloatValue.obtain(aFloat).toIntArray()).toList();
                    }
                }))
                .add(0x72, 0xE8, 0x7D, 0x7D)
                .toInts();

        byte[] bytes = ConvertTool.toBytes(ints);
        byte[] bytes1 = ConvertTool.getBytes(bytes, 2, 4);


    }
}
