package com.codingtu.cooltu.lib4a;

import org.junit.Test;

import static org.junit.Assert.*;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4a.tools.zip.ZipUiThread;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.tools.Zip;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {

        Zip.src("xx").progress(new ZipUiThread.OnProgress() {
            @Override
            public void progress(long totalLen, long currentLen) {

            }
        }).zipWithThread();

        assertEquals(4, 2 + 2);
    }
}