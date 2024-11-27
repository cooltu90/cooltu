package core.msthread;

public class TesUdBaseForMsThread implements TesUdMsThreadInterface {

    protected TesUdMsThread tesUdMsThread;

    public TesUdBaseForMsThread() {
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
