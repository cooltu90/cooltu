package cooltu.processor.worker.model;


import cooltu.constant.Constant;
import cooltu.constant.FullName;
import cooltu.processor.modelinterface.DialogForToastMethodModelInterface;
import cooltu.processor.worker.model.base.SubBaseModel;

public class DialogForToastMethodModel extends SubBaseModel implements DialogForToastMethodModelInterface {

    private boolean isAct;

    public DialogForToastMethodModel(boolean isAct) {
        this.isAct = isAct;
    }

    @Override
    public void setTagFor_className(StringBuilder sb) {
        addTag(sb, FullName.TOAST_DIALOG_VIEW);
    }

    @Override
    public void setTagFor_act(StringBuilder sb) {
        if (isAct) {
            addTag(sb, "getThis()");
        } else {
            addTag(sb, "getActivity()");
        }
    }

    @Override
    public void setTagFor_layout(StringBuilder sb) {
        addTag(sb, Constant.DEFAULT_TOAST_DIALOG_LAYOUT);
    }
}
/* model_temp_start
    private [[className]] toastDialog;

    protected [[className]] getToastDialog() {
        if (toastDialog == null)
            toastDialog = new [[className]]([[act]])
                    .setLayout([[layout]])
                    .build();
        return toastDialog;
    }

    protected void toastShow(String msg) {
        [[className]] td = getToastDialog();
        td.setContent(msg);
        td.show();
    }

    protected void toastSet(String msg) {
        if (core.lib4j.tools.StringTool.isNotBlank(msg)) {
            getToastDialog().setContent(msg);
        }
    }

    protected void toastHidden(String msg, long time, core.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        toastSet(msg);
        core.lib4a.tools.HandlerTool.getMainHandler().postDelayed(() -> getToastDialog().hidden(onHiddenFinished), time);
    }

    protected void toastHidden(String msg, core.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        toastHidden(msg, 1000, onHiddenFinished);
    }

    protected void toastHidden(String msg) {
        toastHidden(msg, null);
    }
model_temp_end */
