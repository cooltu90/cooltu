package cooltu.processor.annotation.ui.dialog;

public @interface DialogUse {
    String name() default "dialog";

    String title();

    String content();

    Class objType() default Object.class;

    Class base() default Void.class;
}
