package core.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class SubThreadActivityMsThread extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
    private Handler subHandler0;
    private Handler subHandler1;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler0();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler1();
            }
        }).start();

    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }

    private void createSubHandler0() {
        Looper.prepare();
        subHandler0 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread0(msg);
            }
        };
        sendMessage(subHandler0, subThread0StartType());
        Looper.loop();
    }

    private void createSubHandler1() {
        Looper.prepare();
        subHandler1 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread1(msg);
            }
        };
        sendMessage(subHandler1, subThread1StartType());
        Looper.loop();
    }


    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private SubThreadActivityMsThreadInterface dealer;

    public static SubThreadActivityMsThread obtain() {
        return new SubThreadActivityMsThread();
    }

    public SubThreadActivityMsThread dealer(SubThreadActivityMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(SubThreadActivityMsThreadType type) {
        return type.ordinal();
    }

    protected boolean isSubThread0() {
        return Thread.currentThread() == subHandler0.getLooper().getThread();
    }

    protected boolean isSubThread1() {
        return Thread.currentThread() == subHandler1.getLooper().getThread();
    }


    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST_1)) {
            Object[] objects = (Object[]) msg.obj;
            dealer.dealToast((java.lang.String) objects[0], (int) objects[1]);
            return;
        }
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST_0)) {
            dealer.dealToast((java.lang.String) msg.obj);
            return;
        }

    }

    public boolean sendMessageForDealToast(java.lang.String str, int age) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(SubThreadActivityMsThreadType.DEAL_TOAST_1), str, age);
            return true;
        }
        return false;
    }

    public boolean sendMessageForDealToast(java.lang.String str) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(SubThreadActivityMsThreadType.DEAL_TOAST_0), str);
            return true;
        }
        return false;
    }


    ///////////////////////////////////////////////////////
    //
    // 线程0的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread0StartType() {
        return 0;
    }

    private void handleMessageInThread0(Message msg) {
    }

    ///////////////////////////////////////////////////////
    //
    // 线程1的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread1StartType() {
        return 0;
    }

    private void handleMessageInThread1(Message msg) {
    }

}

