package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class DefaultEditDialogLayoutDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        Constant.DEFAULT_EDIT_DIALOG_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultEditDialogLayout.class,
                                te.getAnnotation(DefaultEditDialogLayout.class).value())
                        .toString();
    }
}
