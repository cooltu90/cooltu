package cooltu.constant;

import java.io.File;
import java.util.List;

public class Constant {
    /**********************************************
     * name名类型：以（com.a.b.c.TestClassActivity为例）
     * allName：
     *     说明：什么类型的名字都行
     *     例子：test_class_activity,TestClassActivity,TEST_CLASS_ACTIVITY,testClassActivity
     * allBaseName：
     *     说明：什么类型的基础名字都行,不包括后缀
     *     例子：test_class,TestClass,TEST_CLASS,testClass
     * typeName：
     *     说明：类型名
     *     例子：TestClassActivity
     * typeBaseName：
     *     说明：基础类型名
     *     列子：TestClass
     * staticName：
     *     说明：静态名字
     *     例子：TEST_CLASS_ACTIVITY
     * staticBaseName：
     *     说明：基础静态名字
     *     例子：TEST_CLASS
     * methodName：
     *     说明：方法名字
     *     例子：testClassActivity
     * methodBaseName：
     *     说明：方法基础名字
     *     例子：testClass
     * layoutName：
     *     说明：布局样式名字
     *     例子：test_class_activity
     * layoutBaseName：
     *     说明：布局样式基础名字
     *     例子：test_class
     * fullName：
     *     说明：全名，包括包名
     *     例子：com.a.b.c.TestClassActivity
     **********************************************/
    //文件分割
    public static final String SEPARATOR = File.separator;
    //各种类名
    public static final String NAME_ACT_START = "ActStart";
    public static final String NAME_ACT_BACK_INTENT = "ActBackIntent";
    public static final String NAME_PASS = "Pass";
    public static final String NAME_NET = "Net";
    public static final String NAME_MOCKS = "Mocks";
    public static final String NAME_PERMISSION = "Permissions";
    public static final String NAME_CODE_4_REQUEST = "Code4Request";
    //from名
    public static final String FROM_ACT = "fromAct";
    //布局前缀
    public static final String LAYOUT_PREFIX_ACTIVITY = "activity";
    public static final String LAYOUT_PREFIX_FRAGMENT = "fragment";
    public static final String LAYOUT_PREFIX_ITEM = "item";
    //各种后缀
    public static final String SUFFIX_ACTIVITY = "Activity";
    public static final String SUFFIX_DEAL = "Deal";
    public static final String SUFFIX_VH = "VH";
    public static final String SUFFIX_ADAPTER = "Adapter";
    public static final String SUFFIX_ACTIVITY_BASE = "ActivityBase";
    public static final String SUFFIX_FRAGMENT = "Fragment";
    public static final String SUFFIX_FRAGMENT_BASE = "FragmentBase";
    public static final String SUFFIX_API_SERVICE = "Service";
    public static final String SUFFIX_SEND_PARAMS = "Params";
    public static final String SUFFIX_NET_BACK = "Back";
    public static final String SUFFIX_MOCK = "Mock";
    public static final String SUFFIX_MODEL = "Model";
    public static final String SUFFIX_INTERFACE = "Interface";
    //Intent的key名
    public static final String INTENT_NAME_RESULT_NAME = "resultName";
    //rType
    public static final String R_TYPE_LAYOUT = "layout";
    //delete_acts
    public static List<String> DELETE_ACTS;
    public static List<String> RENAME_ACTS;
    //module
    public static String CURRENT_MODULE;
    public static final String MODULE_NAME_APP = "app";
    public static final String MODULE_NAME_APP_TEST = "app_test";
    public static final String MODULE_NAME_CORE_LIB4J = "core_lib4j";
    public static final String MODULE_NAME_CORE_LIB4A = "core_lib4a";
    public static final String MODULE_NAME_CORE_PROCESSOR = "core_processor";
    public static final String MODULE_NAME_CORE_PROCESSOR_MODEL_INTERFACE = "core_processor_model_interface";
    //path
    public static final String PATH_SRC_MAIN = "\\src\\main";
    public static final String PATH_SRC_MAIN_JAVA = "\\src\\main\\java";
    public static final String PATH_SRC_MAIN_RES_LAYOUT = "\\src\\main\\res\\layout";
    //package
    public static String PKG_DEFAULT_R;
    public static String PKG_MOCK;
    public static String DEFAULT_DIALOG_LAYOUT;
    public static String DEFAULT_TOAST_DIALOG_LAYOUT;
    public static String DEFAULT_EDIT_DIALOG_LAYOUT;
    public static String DEFAULT_TEMP_LAYOUT;
    //apt包名
    public static final String PKG_DEAL = "core.processor.worker.deal";
    public static final String PKG_MODEL_INTERFACE = "core.processor.modelinterface";
    public static final String PKG_MODEL = "core.processor.worker.model";
    //
    public static final String PKG_ACT_BASE = "core.actbase";
    public static final String PKG_FRAGMENT_BASE = "core.fragmentbase";
    public static final String PKG_CORE_TOOLS = "core.tools";
    public static final String PKG_API_SERVICE = "core.tools.net.api";
    public static final String PKG_SEND_PARAMS = "core.tools.net.params";
    public static final String PKG_NET_BACK = "core.tools.net.back";
    public static final String PKG_VH = "core.vh";
    public static final String PKG_CORE_LIB4A = "core.lib4a";
    public static final String PKG_R_LAYOUT = "R.layout.";
    // 默认的文件包名
    public static final String PKG_DEFAULT_NET_MOCK = ".net.mock";
    public static final String PKG_DEFAULT_UI_ADAPTER = ".ui.adapter";
    public static final String PKG_DEFAULT_UI = ".ui";
    //
    public static final String CODE_NAME_GET_PIC_BY_CAMERA = "GET_PIC_BY_CAMERA";
    public static final String CODE_NAME_GET_PIC_BY_GALLERY = "GET_PIC_BY_GALLERY";
    public static final String CODE_NAME_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION = "MANAGE_APP_ALL_FILES_ACCESS_PERMISSION";

    //文件后缀
    public static final String FILE_TYPE_JAVA = ".java";
    public static final String FILE_TYPE_XML = ".xml";
    public static final String FILE_TYPE_JPG = ".jpg";
    public static final String FILE_TYPE_TXT = ".txt";
    public static final String FILE_TYPE_ZIP = ".zip";
    //文件名
    public static final String FILE_NAME_ANDROID_MANIFEST = "AndroidManifest.xml";
    //方法名前缀
    public static final String PREFIX_METHOD_SET_TAG_FOR = "setTagFor_";

    public static final String LAYOUT_NAME_DEFAULT_TEMP = "default_temp_layout";
}
