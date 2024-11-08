package core.msthread;

import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMSThread;

public class SubThreadActivityMsThread extends CoreMSThread {

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

    @Override
    protected int subThreadStartType() {
        return type(SubThreadActivityMsThreadType.DEAL_DATA_START);
    }


    @Override
    protected void handleMessageInThread(Message msg) {
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_DATA_START)) {
            dealer.dealDataStart();
            return;
        }
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_DATA_START1)) {
            dealer.dealDataStart1((int) msg.obj);
            return;
        }

    }

    @Override
    protected void handleMessageInMain(Message msg) {
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST)) {
            dealer.dealToast((java.lang.String) msg.obj);
            return;
        }
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST1)) {
            Object[] objects = (Object[]) msg.obj;
            dealer.dealToast1((java.lang.String) objects[0], (int) objects[1]);
            return;
        }

    }

    public boolean sendMessageForDealToast(java.lang.String str) {
        if (isSubThread()) {
            sendMainMessage(type(SubThreadActivityMsThreadType.DEAL_TOAST),  str);
            return true;
        }
        return false;
    }
    public boolean sendMessageForDealToast1(java.lang.String str, int age) {
        if (isSubThread()) {
            sendMainMessage(type(SubThreadActivityMsThreadType.DEAL_TOAST1),  str,  age);
            return true;
        }
        return false;
    }
    public boolean sendMessageForDealDataStart() {
        if (isMainThread()) {
            sendSubMessage(type(SubThreadActivityMsThreadType.DEAL_DATA_START));
            return true;
        }
        return false;
    }
    public boolean sendMessageForDealDataStart1(int num) {
        if (isMainThread()) {
            sendSubMessage(type(SubThreadActivityMsThreadType.DEAL_DATA_START1),  num);
            return true;
        }
        return false;
    }

}

