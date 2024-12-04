package core.msthread;

public class TestMsThreadBaseForMsThread implements TestMsThreadMsThreadInterface {

    protected TestMsThreadMsThread testMsThreadMsThread;

    public void start() {
        testMsThreadMsThread = TestMsThreadMsThread.obtain().dealer(this);
        testMsThreadMsThread.start();
    }

    @Override
    public void toast() {
    }

    protected boolean sendMessageForToast(long delayMillis) {
        return testMsThreadMsThread.sendMessageForToast(delayMillis);
    }
    @Override
    public void toast(java.lang.String msg) {
    }

    protected boolean sendMessageForToast(java.lang.String msg) {
        return testMsThreadMsThread.sendMessageForToast(msg);
    }
    @Override
    public void toast(int age) {
    }

    protected boolean sendMessageForToast(int age) {
        return testMsThreadMsThread.sendMessageForToast(age);
    }


    protected void stopMsThread() {
        if (testMsThreadMsThread != null)
            testMsThreadMsThread.stop();
        testMsThreadMsThread = null;
    }

}
