package cooltu.processor.annotation.ui.dialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface EdParam {
    String value();
}
