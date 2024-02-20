package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layerview.RelativeLayerView;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.ts.Ts;

import java.util.ArrayList;
import java.util.List;

public class MenuDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private RelativeLayerView rlv;
    private View inflate;
    private int layout;
    private int itemLayout;
    private String title;
    private Object obj;
    private LinearLayout itemLl;
    private List<Integer> itemIds;
    private List<String> itemNames;
    private View.OnClickListener onClickListener;
    private List<View> itemViews;
    private ShowItem showItem;

    @Override
    public void onClick(View v) {
        if (obj != null)
            v.setTag(R.id.tag_0, obj);
        onClickListener.onClick(v);
        rlv.hidden();
    }

    @Override
    public void destroy() {
        act = null;
        rlv = null;
        inflate = null;
        title = null;
        obj = null;
        itemLl = null;
        if (itemIds != null) {
            itemIds.clear();
            itemIds = null;
        }
        if (itemNames != null) {
            itemNames.clear();
            itemNames = null;
        }
        onClickListener = null;
        if (itemViews != null) {
            itemViews.clear();
            itemViews = null;
        }
        showItem = null;
    }

    public MenuDialog(Activity act) {
        this.act = act;
    }

    public MenuDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public MenuDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public MenuDialog setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public MenuDialog setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
        return this;
    }

    public MenuDialog setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public MenuDialog setItem(int viewId, String itemName) {
        if (itemIds == null) {
            itemIds = new ArrayList<>();
        }
        itemIds.add(viewId);

        if (itemNames == null) {
            itemNames = new ArrayList<>();
        }
        itemNames.add(itemName);

        return this;
    }

    public MenuDialog setShowItem(ShowItem showItem) {
        this.showItem = showItem;
        return this;
    }

    public static interface ShowItem {
        public boolean showItem(int viewId, Object obj);
    }

    public MenuDialog build() {
        rlv = new RelativeLayerView(act);
        ViewTool.addToAct(act, rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        ViewTool.gone(rlv);
        //设置标题
        ViewTool.setText(inflate.findViewById(R.id.menuDialogTitleTv), title);
        itemLl = inflate.findViewById(R.id.menuDialogItemsLl);
        itemViews = new ArrayList<>();
        Ts.ls(itemIds, new Ts.EachTs<Integer>() {
            @Override
            public boolean each(int position, Integer viewId) {
                View inflate = InflateTool.inflate(itemLayout);
                itemViews.add(inflate);
                itemLl.addView(inflate, ViewTool.MATCH_PARENT, ViewTool.WRAP_CONTENT);
                ViewTool.setText(inflate.findViewById(R.id.menuDialogItemTv), itemNames.get(position));
                inflate.setId(viewId);
                inflate.setOnClickListener(MenuDialog.this);
                return false;
            }
        });

        ViewTool.inRelativeCenter(inflate);
        return this;
    }


    public void show() {
        Ts.ls(itemViews, new Ts.EachTs<View>() {
            @Override
            public boolean each(int position, View view) {
                ViewTool.visibleOrGone(view, showItem.showItem(view.getId(), obj));
                return false;
            }
        });
        rlv.show();
    }


}
