package core.msthread;

public class TesUdBaseForMsThread<THIS extends TesUdBaseForMsThread> extends com.codingtu.cooltu.test.TestUdBase<THIS> implements TesUdMsThreadInterface {

    protected TesUdMsThread tesUdMsThread;

    public void start() {
        tesUdMsThread = TesUdMsThread.obtain().dealer(this);
        tesUdMsThread.start();
    }

    @Override
    public void subStart() {
    }

    protected boolean sendMessageForSubStart() {
        return tesUdMsThread.sendMessageForSubStart();
    }
    @Override
    public void toast(java.lang.String msg) {
    }

    protected boolean sendMessageForToast(java.lang.String msg) {
        return tesUdMsThread.sendMessageForToast(msg);
    }

}
