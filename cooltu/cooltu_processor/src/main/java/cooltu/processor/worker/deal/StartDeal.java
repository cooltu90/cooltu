package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.ui.Start;
import cooltu.processor.annotation.ui.StartGroup;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.ActBaseModel;
import cooltu.processor.worker.model.PassModel;
import cooltu.processor.worker.model.StartModel;

public class StartDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        String simpleName = ElementTools.simpleName(element);
        if (simpleName.endsWith(Constant.SUFFIX_ACTIVITY)) {
            String actFullName = ElementTools.getType(element);
            String actStaticName = ElementTools.staticSimpleName(element);
            StartModel.model.addStart(actFullName, actStaticName, null);
        } else {
            ListValueMap<Integer, KV<String, String>> ikv = new ListValueMap<>();
            Ts.ls(element.getEnclosedElements(), new Each<Element>() {
                @Override
                public boolean each(int position, Element element) {
                    if (element instanceof VariableElement) {
                        KV<String, String> kv = ElementTools.getFiledKv((VariableElement) element);
                        PassModel.model.add(kv);

                        int[] group = null;
                        StartGroup startGroup = element.getAnnotation(StartGroup.class);
                        if (startGroup != null) {
                            group = startGroup.value();
                        }
                        if (CountTool.isNull(group)) {
                            ikv.get(0).add(kv);
                        } else {
                            Ts.ls(group, new Each<Integer>() {
                                @Override
                                public boolean each(int position, Integer integer) {
                                    ikv.get(integer).add(kv);
                                    return false;
                                }
                            });
                        }
                    }
                    return false;
                }
            });
            Start start = element.getAnnotation(Start.class);
            String actClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return start.value();
                }
            });
            String actFullName = null;
            String actStaticName = null;
            if (ClassTool.isVoid(actClass)) {
                Element parentElement = element.getEnclosingElement();
                actFullName = ElementTools.getType(parentElement);
                actStaticName = ElementTools.staticSimpleName(parentElement);
            } else {
                actFullName = actClass;
                actStaticName = NameTools.toStaticType(NameTools.getJavaSimpleName(actFullName));
            }

            try {
                ActBaseModel actBaseModel = ActBaseDeal.getActBaseModel(actFullName);
                actBaseModel.addStartParams(ikv);
            }catch (Exception e){

            }
            StartModel.model.addStart(actFullName, actStaticName, ikv);
        }
    }

}
