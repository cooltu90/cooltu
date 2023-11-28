package com.codingtu.cooltu.cryption.tools;

import com.codingtu.cooltu.cryption.types.Type0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class CryptionFile {

    protected CryptionListener listener = new CryptionListener() {
        @Override
        public void log(String s) {
            System.out.println(s);
        }
    };
    protected File file;
    protected byte[] pswBytes;

    protected Type0 type;

    protected FileInputStream ipt;
    protected FileOutputStream opt;

    int lastPercent = 0;

    public CryptionFile(File file, byte[] pswBytes, CryptionListener listener) {
        this.file = file;
        this.pswBytes = pswBytes;
        type = CryptionTypes.getDefaultType();
        if (listener != null)
            this.listener = listener;
    }

    public void start() {
        try {
            startWithException();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ipt != null) {
                try {
                    ipt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ipt = null;
            }

            if (opt != null) {
                try {
                    opt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                opt = null;
            }
        }
        finish();
    }

    protected void finish() {
    }

    protected abstract void startWithException() throws IOException;


    protected void percent(long readLen, long totalLen) {
        int percent = (int) (readLen * 10 / totalLen);
        printPercent(lastPercent, percent);
        lastPercent = percent;
    }

    protected void printPercent(int lastPercent, int percent) {
        percent -= lastPercent;
        for (int i = 0; i < percent; i++) {
            System.out.print("====");
        }
    }


    protected byte[] encode(byte[] bytes) {
        return encode(bytes, bytes.length);
    }

    protected byte[] encode(byte[] bytes, int len) {
        return this.type.encode(bytes, pswBytes, len);
    }

    protected String read(byte[] bytes, int len) throws IOException {
        ipt.read(bytes, 0, len);
        encode(bytes, len);
        return new String(bytes, 0, len, CryptionTools.CHARSET);
    }

}
