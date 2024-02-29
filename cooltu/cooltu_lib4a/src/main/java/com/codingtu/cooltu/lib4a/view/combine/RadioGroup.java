package com.codingtu.cooltu.lib4a.view.combine;

import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;

import java.util.ArrayList;
import java.util.List;

public class RadioGroup<T> implements OnDestroy, View.OnClickListener {
    private boolean hasNull;
    private int selected = -1;
    //private View[] bts;
    private BaseTs<View> bts;
    //private int[] bgs;
    private List<OnSelectChange> onSelectChanges;
    private OnSetItem onSetItem;
    private BaseTs<T> items;

    public RadioGroup() {

    }

    public static interface OnSelectChange {
        public void onChange(int selected);
    }

    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static <T> RadioGroup<T> obtain(Destroys destroys) {
        RadioGroup<T> radioGroup = new RadioGroup<T>();
        destroys.add(radioGroup);
        return radioGroup;
    }


    public RadioGroup<T> setHasNull(boolean hasNull) {
        this.hasNull = hasNull;
        return this;
    }

//    public RadioGroup<T> setBgs(int... bgs) {
//        this.bgs = bgs;
//        initBgs();
//        return this;
//    }

    public RadioGroup<T> setBts(View... bts) {
        this.bts = Ts.ts(bts);
        this.bts.ls(new Ts.EachTs<View>() {
            @Override
            public boolean each(int position, View view) {
                view.setTag(R.id.tag_0, position);
                view.setOnClickListener(RadioGroup.this);
                return false;
            }
        });
        return this;
    }

    public RadioGroup<T> setBts(ViewGroup vp) {
        if (vp.getChildCount() > 0) {
            this.bts = Ts.get(vp.getChildCount(), new Ts.EachGetter<View>() {
                @Override
                public View get(int i) {
                    View childAt = vp.getChildAt(i);
                    childAt.setTag(R.id.tag_0, i);
                    childAt.setOnClickListener(RadioGroup.this);
                    return childAt;
                }
            });
        }
        //initBgs();
        return this;
    }

//    private void initBgs() {
//        if (bgs != null && bts != null && bgs.length == 2) {
//            int[] ints = new int[bts.length << 1];
//            for (int i = 0; i < ints.length; i += 2) {
//                ints[i] = bgs[0];
//                ints[i + 1] = bgs[1];
//            }
//            this.bgs = ints;
//        }
//    }

    public RadioGroup<T> addOnSelectChange(OnSelectChange onSelectChange) {
        if (onSelectChanges == null) {
            onSelectChanges = new ArrayList<>();
        }
        onSelectChanges.add(onSelectChange);
        return this;
    }


    public RadioGroup<T> setOnSetItem(OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }


    /**************************************************
     *
     *
     *
     **************************************************/

//    public RadioGroup(Destroys destroys, boolean hasNull, int selected, int[] bgs, View... bts) {
//        this(destroys, hasNull, selected, bgs, null, bts);
//    }

//    public RadioGroup(Destroys destroys, boolean hasNull, int selected, int[] bgs, OnSetItem onSelectChangeItem, View... bts) {
//        destroys.add(this);
//        this.selected = selected;
//        this.hasNull = hasNull;
//        this.onSetItem = onSelectChangeItem;
//        this.bgs = bgs;
//        this.bts = bts;
//        if (bgs.length == 2) {
//            int[] ints = new int[bts.length << 1];
//            for (int i = 0; i < ints.length; i += 2) {
//                ints[i] = bgs[0];
//                ints[i + 1] = bgs[1];
//            }
//            this.bgs = ints;
//        }
//
//        for (int i = 0; i < bts.length; i++) {
//            bts[i].setTag(R.id.tag_0, i);
//            bts[i].setOnClickListener(this);
//        }
//
//        change();
//    }

    /**************************************************
     *
     *
     *
     **************************************************/

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag(R.id.tag_0);
        setSelected(index);
    }


    public RadioGroup<T> setSelected(int index) {
        if (index == selected) {
            if (hasNull) {
                setSelectedReal(-1);
            }
        } else {
            setSelectedReal(index);
        }
        return this;
    }

    private void setSelectedReal(int index) {
        this.selected = index;
        change();
        for (int i = 0; i < CountTool.count(onSelectChanges); i++) {
            onSelectChanges.get(i).onChange(this.selected);
        }
    }

    private void change() {
        bts.ls(new Ts.EachTs<View>() {
            @Override
            public boolean each(int i, View view) {
                if (selected == i) {
                    if (onSetItem != null) {
                        onSetItem.setSelected(view);
                    }
                } else {
                    if (onSetItem != null) {
                        onSetItem.setSelectno(view);
                    }
                }
                return false;
            }
        });
    }


    public int getSelected() {
        return selected;
    }


    @Override
    public void destroy() {
        if (bts != null) {
            bts.ls(new Ts.EachTs<View>() {
                @Override
                public boolean each(int position, View view) {
                    view.setOnClickListener(null);
                    return false;
                }
            }).clear();
        }
        bts = null;
        if (onSelectChanges != null) {
            onSelectChanges.clear();
            onSelectChanges = null;
        }
        onSetItem = null;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public RadioGroup<T> setItems(T... items) {
        this.items = Ts.ts(items);
        return this;
    }

    public RadioGroup<T> addItems(T item) {
        if (this.items == null) {
            this.items = Ts.ts(item);
        } else {
            this.items.add(item);
        }
        return this;
    }

    public T getCurrentItem() {
        return this.items.get(getSelected());
    }
}
