package com.codingtu.cooltu.lib4a.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.ui.adapter.viewholder.CoreAdapterVH;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.CoreTs;
import com.codingtu.cooltu.lib4j.ts.Ts;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class CoreListAdapter<VH extends CoreAdapterVH, T> extends CoreAdapter<VH> {
    protected BaseTs<T> ts;
    private Class<VH> vhClass;

    @Override
    public int getItemCount() {
        return CountTool.count(ts);
    }

    public void updateItems(List<T> ts) {
        updateItems(Ts.ts(ts));
    }

    public void updateItems(T... ts) {
        updateItems(Ts.ts(ts));
    }

    public void updateItems(BaseTs<T> ts) {
        this.ts = ts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Constructor<VH> constructor = this.vhClass.getConstructor(ViewGroup.class);
            return (VH) constructor.newInstance(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setVH(Class vhClass) {
        this.vhClass = vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindVH(holder, position, this.ts.get(position));
    }

    protected abstract void onBindVH(@NonNull VH holder, int position, T t);

    public BaseTs<T> getItems() {
        return this.ts;
    }

    public void addItem(T t) {
        if (this.ts == null) {
            this.ts = new BaseTs<>();
        }
        this.ts.add(t);
        notifyDataSetChanged();
    }
}
