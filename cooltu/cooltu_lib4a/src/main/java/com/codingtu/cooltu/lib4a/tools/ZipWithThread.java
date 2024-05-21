package com.codingtu.cooltu.lib4a.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.tools.Zip;

import java.io.File;

public class ZipWithThread implements OnDestroy {

    public static final int ERROR = 0;
    public static final int START = 1;
    public static final int PROGRESS = 2;
    public static final int FINISH = 3;

    private Handler handler;
    private Zip zip;
    private Zip.OnProgress onProgress;
    private Zip.OnError onError;
    private Zip.OnFinish onFinish;
    private Zip.OnStart onStart;

    @Override
    public void destroy() {
        handler = null;
        onProgress = null;
        onError = null;
        onStart = null;
        onFinish = null;
        zip = null;
    }

    private ZipWithThread(File src) {
        zip = Zip.src(src);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case PROGRESS:
                        //progress
                        Progress progress = (Progress) msg.obj;
                        if (onProgress != null) {
                            onProgress.onProgress(progress.totalLen, progress.currentLen);
                        }
                        break;
                    case ERROR:
                        Throwable throwable = (Throwable) msg.obj;
                        if (onError != null) {
                            onError.onError(throwable);
                        }
                        destroy();
                    case FINISH:
                        File file = (File) msg.obj;
                        if (onFinish != null) {
                            onFinish.onFinish(file);
                        }
                        destroy();
                    case START:
                        if (onStart != null) {
                            onStart.onStart();
                        }
                }

            }
        };
    }

    public static ZipWithThread src(File src) {
        return new ZipWithThread(src);
    }

    public static ZipWithThread src(String srcPath) {
        return src(new File(srcPath));
    }

    public ZipWithThread desc(File desc) {
        zip.desc(desc);
        return this;
    }

    public ZipWithThread desc(String descPath) {
        return desc(new File(descPath));
    }

    public ZipWithThread progress(Zip.OnProgress onProgress) {
        this.onProgress = onProgress;
        zip.progress(new Zip.OnProgress() {
            @Override
            public void onProgress(long totalLen, long zipedLen) {
                Message msg = Message.obtain();
                msg.what = PROGRESS;
                msg.obj = new Progress(totalLen, zipedLen);
                handler.sendMessage(msg);
            }
        });
        return this;
    }

    public ZipWithThread pass(Zip.Pass pass) {
        zip.pass(pass);
        return this;
    }

    public ZipWithThread error(Zip.OnError onError) {
        this.onError = onError;
        zip.error(new Zip.OnError() {
            @Override
            public void onError(Throwable throwable) {
                Message msg = Message.obtain();
                msg.what = ERROR;
                msg.obj = throwable;
                handler.sendMessage(msg);
            }
        });
        return this;
    }

    public ZipWithThread finish(Zip.OnFinish onFinish) {
        this.onFinish = onFinish;
        zip.finish(new Zip.OnFinish() {
            @Override
            public void onFinish(File file) {
                Message msg = Message.obtain();
                msg.what = FINISH;
                msg.obj = file;
                handler.sendMessage(msg);
            }
        });
        return this;
    }

    public ZipWithThread zipedPathDeal(Zip.ZipedPathDeal zipedPathDeal) {
        zip.zipedPathDeal(zipedPathDeal);
        return this;
    }

    public ZipWithThread cacheSize(int cacheSize) {
        zip.cacheSize(cacheSize);
        return this;
    }


    public ZipWithThread start(Zip.OnStart onStart) {
        this.onStart = onStart;
        zip.start(new Zip.OnStart() {
            @Override
            public void onStart() {
                Message msg = Message.obtain();
                msg.what = START;
                handler.sendMessage(msg);
            }
        });
        return this;
    }

    public void zip() {
        OnceThread.sub(new Runnable() {
            @Override
            public void run() {
                zip.zip();
            }
        }).main(new OnceThread.MainRunnable() {
            @Override
            public void run(Throwable throwable) {

            }
        }).start();
    }

}
