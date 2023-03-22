package cooltu.processor;

import cooltu.processor.annotation.ModuleInfo;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class
        };
    }

}
