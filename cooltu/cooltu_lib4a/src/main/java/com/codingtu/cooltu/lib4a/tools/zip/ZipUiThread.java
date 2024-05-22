package com.codingtu.cooltu.lib4a.tools.zip;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.tools.Zip;

import java.io.File;

public class ZipUiThread {

    public static abstract class OnProgress implements Zip.OnProgress {

        private final Handler handler;

        public OnProgress() {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Progress progress = (Progress) msg.obj;
                    progress(progress.totalLen, progress.currentLen);
                }
            };
        }

        public abstract void progress(long totalLen, long currentLen);

        @Override
        public final void onProgress(long totalLen, long zipedLen) {
            Message msg = Message.obtain();
            msg.obj = new Progress(totalLen, zipedLen);
            handler.sendMessage(msg);
        }
    }

    public static abstract class OnFinish implements Zip.OnFinish {

        private final Handler handler;

        public OnFinish() {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    File file = (File) msg.obj;
                    finish(file);
                }
            };
        }

        public abstract void finish(File file);

        @Override
        public final void onFinish(File file) {
            Message msg = Message.obtain();
            msg.obj = file;
            handler.sendMessage(msg);
        }
    }

    public static abstract class OnError implements Zip.OnError {

        private final Handler handler;

        public OnError() {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Throwable throwable = (Throwable) msg.obj;
                    error(throwable);
                }
            };
        }

        public abstract void error(Throwable throwable);

        @Override
        public final void onError(Throwable throwable) {
            Message msg = Message.obtain();
            msg.obj = throwable;
            handler.sendMessage(msg);
        }
    }

    public static abstract class OnStart implements Zip.OnStart {

        private final Handler handler;

        public OnStart() {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    start();
                }
            };
        }

        public abstract void start();

        @Override
        public final void onStart() {
            Message msg = Message.obtain();
            handler.sendMessage(msg);
        }
    }

}
