package cooltu.processor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ModuleInfo {

    //模块名
    String module();

    //基础的Activity
    Class baseAct();

    //基础的Fragment
    Class baseFragment();

    //默认的R的包名
    String pkg();

}