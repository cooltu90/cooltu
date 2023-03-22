package cooltu.processor.worker.model;


import java.util.HashMap;
import java.util.List;

import cooltu.constant.Index;
import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.form.FormType;
import cooltu.processor.lib.bean.FormItemInfo;
import cooltu.processor.lib.tools.FormTool;
import cooltu.processor.modelinterface.BindHandlerModelInterface;
import cooltu.processor.worker.model.base.SubBaseModel;

public class BindHandlerModel extends SubBaseModel implements BindHandlerModelInterface {

    private String type;
    private String bean;
    private ListValueMap<Integer, FormItemInfo> formItemMap;
    private HashMap<String, String> needLinkMap;


    public void setType(String type) {
        this.type = type;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public void setCases(ListValueMap<Integer, FormItemInfo> formItemMap) {
        this.formItemMap = formItemMap;
    }

    public void setNeedLinks(HashMap<String, String> needLinkMap) {
        this.needLinkMap = needLinkMap;
    }

    @Override
    public void setTagFor_type(StringBuilder sb) {
        addTag(sb, type);
    }

    @Override
    public void setTagFor_bean(StringBuilder sb) {
        addTag(sb, bean);
    }

    @Override
    public void setTagFor_cases(StringBuilder sb) {
        setTagFor_cases(sb, formItemMap.get(FormType.EDIT_TEXT), Index.EDIT_TEXT);
        setTagFor_cases(sb, formItemMap.get(FormType.TEXT_VIEW), Index.TEXT_VIEW);
        setTagFor_cases(sb, formItemMap.get(FormType.RADIO_GROUP), Index.RADIO_GROUP);
    }

    private void setTagFor_cases(StringBuilder sb, List<FormItemInfo> etTvItems, int index) {
        if (!CountTool.isNull(etTvItems)) {
            addLnTag(sb, "            if (msg.what == [0]) {", index);
            addLnTag(sb, "                switch (msg.arg1) {");


            Ts.ls(etTvItems, new Each<FormItemInfo>() {
                @Override
                public boolean each(int position, FormItemInfo info) {

                    addLnTag(sb, "                    case [1]:", position);
                    addLnTag(sb, "                        [xxx] = [toBean];", info.beanField, FormTool.toBean(info));

                    String viewId = info.viewId.toString();
                    if (needLinkMap.containsKey(viewId)) {
                        addLnTag(sb, "                        link([R.id.t1Et]);", viewId);
                    }

                    addLnTag(sb, "                        break;");

                    return false;
                }
            });

            addLnTag(sb, "                }");
            addLnTag(sb, "            }");

        }
    }

}

/* model_temp_start
    public static class BindHandler extends android.os.Handler {
        private [[type]] [[bean]];

        public BindHandler([[type]] [[bean]]) {
            this.[[bean]] = [[bean]];
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
[[cases]]

        }


        private core.lib4j.data.map.ListValueMap<Integer, core.processor.lib.form.FormLink> links;

        private core.lib4j.data.map.ListValueMap<Integer, core.processor.lib.form.FormLink> getLinks() {
            if (links == null) {
                links = new core.lib4j.data.map.ListValueMap<>();
            }
            return links;
        }

        public void addLink(int index, core.processor.lib.form.FormLink link) {
            getLinks().get(index).add(link);
        }

        private void link(int id) {
            core.lib4j.ls.Ls.ls(getLinks().get(id), new core.lib4j.ls.each.Each<core.processor.lib.form.FormLink>() {
                @Override
                public boolean each(int position, core.processor.lib.form.FormLink formLink) {
                    formLink.link();
                    return false;
                }
            });
        }
    }
model_temp_end */
