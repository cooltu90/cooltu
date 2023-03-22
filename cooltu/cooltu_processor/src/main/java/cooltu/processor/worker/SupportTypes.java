package cooltu.processor.worker;


import cooltu.processor.annotation.ModuleInfo;
import cooltu.processor.annotation.UseParams;
import cooltu.processor.annotation.create.CreateAct;
import cooltu.processor.annotation.create.CreateAdapter;
import cooltu.processor.annotation.create.CreateFragment;
import cooltu.processor.annotation.delete.DeleteAct;
import cooltu.processor.annotation.form.Form;
import cooltu.processor.annotation.form.FormBean;
import cooltu.processor.annotation.form.FormItem;
import cooltu.processor.annotation.net.Apis;
import cooltu.processor.annotation.net.NetBack;
import cooltu.processor.annotation.permission.Permission;
import cooltu.processor.annotation.rename.RenameAct;
import cooltu.processor.annotation.ui.ActBack;
import cooltu.processor.annotation.ui.ActBase;
import cooltu.processor.annotation.ui.Adapter;
import cooltu.processor.annotation.ui.ClickView;
import cooltu.processor.annotation.ui.DefaultCode;
import cooltu.processor.annotation.ui.DefaultDialogLayout;
import cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import cooltu.processor.annotation.ui.DefaultPass;
import cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import cooltu.processor.annotation.ui.FragmentBase;
import cooltu.processor.annotation.ui.LongClickView;
import cooltu.processor.annotation.ui.Res;
import cooltu.processor.annotation.ui.Start;
import cooltu.processor.annotation.ui.UseDialog;
import cooltu.processor.annotation.ui.UseEditDialog;
import cooltu.processor.annotation.ui.VH;
import cooltu.processor.annotation.ui.dialog.DialogUse;
import cooltu.processor.annotation.ui.dialog.DialogUseForEdit;
import cooltu.processor.annotation.ui.dialog.DialogUseForToast;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {
    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class,
                DefaultDialogLayout.class,
                DefaultToastDialogLayout.class,
                DefaultEditDialogLayout.class,
                DefaultCode.class,
                DefaultPass.class,
                DeleteAct.class,
                RenameAct.class,
                CreateAct.class,
                CreateFragment.class,
                CreateAdapter.class,
                ActBase.class,
                FragmentBase.class,
                UseParams.class,
                Start.class,
                ClickView.class,
                LongClickView.class,
                Res.class,
                Form.class,
                FormBean.class,
                FormItem.class,
                NetBack.class,
                ActBack.class,
                DialogUseForEdit.class,
                DialogUse.class,
                DialogUseForToast.class,
                UseDialog.class,
                UseEditDialog.class,
                Permission.class,
                Adapter.class,
                VH.class,
                Apis.class
        };
    }

}
