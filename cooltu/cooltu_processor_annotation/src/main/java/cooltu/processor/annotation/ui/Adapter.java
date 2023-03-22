package cooltu.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cooltu.constant.AdapterType;


@Retention(RetentionPolicy.SOURCE)
public @interface Adapter {

    Class value() default Void.class;

    int type() default AdapterType.DEFAULT_LIST;

    String rvName() default "rv";

}
