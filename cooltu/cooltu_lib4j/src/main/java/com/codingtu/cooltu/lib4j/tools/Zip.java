package com.codingtu.cooltu.lib4j.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.log.LibLogs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip implements OnDestroy {

    public static interface Pass {
        public boolean pass(File file);
    }

    public static interface ZipedPathDeal {
        public String deal(String path);
    }

    public static interface OnError {
        public void onError(Throwable throwable);

    }

    public static interface OnFinish {
        public void onFinish(File file);
    }


    public static interface OnProgress {
        public void onProgress(long totalLen, long zipedLen);
    }

    public static interface OnStart {
        public void onStart();
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    private File src;
    private File desc;
    private long totalLen;
    private long zipedLen;
    private Integer cacheSize;
    private Pass pass;
    private ZipedPathDeal zipedPathDeal;
    private OnProgress onProgress;
    private OnError onError;
    private OnFinish onFinish;
    private OnStart onStart;
    private long lastTime;

    private Zip(File src) {
        this.src = src;
    }

    @Override
    public void destroy() {
        pass = null;
        zipedPathDeal = null;
        onProgress = null;
        onError = null;
        onFinish = null;
        onStart = null;
    }

    public static Zip src(File src) {
        return new Zip(src);
    }

    public static Zip src(String srcPath) {
        return src(new File(srcPath));
    }

    public Zip desc(File desc) {
        this.desc = desc;
        return this;
    }

    public Zip desc(String descPath) {
        return desc(new File(descPath));
    }

    public Zip progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Zip pass(Pass pass) {
        this.pass = pass;
        return this;
    }

    public Zip error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Zip finish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Zip zipedPathDeal(ZipedPathDeal zipedPathDeal) {
        this.zipedPathDeal = zipedPathDeal;
        return this;
    }

    public Zip cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }


    public Zip start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public void zip() {
        if (!src.exists()) {
            onError(new RuntimeException("没有找到需要压缩打包的文件"));
            return;
        }

        if (desc == null) {
            desc = new File(src.getAbsolutePath() + FileType.d_ZIP);
        }

        if (onProgress != null) {
            onProgress.onProgress(totalLen, 0);
        }

        if (cacheSize == null) {
            cacheSize = 1024 * 512;
        }

        if (onStart != null) {
            onStart.onStart();
        }

        String zipPath = desc.getAbsolutePath();
        totalLen = getLength(src);
        lastTime = System.currentTimeMillis();

        zip(src, zipPath);
        onProgress(totalLen);
        onFinish(desc);
    }

    private void zip(File needPressFile, String zipFilePath) {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFilePath));
            recursivePressFile(out, needPressFile, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    LibLogs.e(e);
                }
            }
        }
    }

    private void recursivePressFile(ZipOutputStream out, File needPressFile, String parentDirName) throws Exception {
        if (needPressFile.isDirectory()) {
            File[] files = needPressFile.listFiles();
            for (File file : files) {
                recursivePressFile(out, file, (StringTool.isBlank(parentDirName) ? ("") : (parentDirName + Constant.SEPARATOR)) + needPressFile.getName());
            }
        } else {
            if (pass != null && pass.pass(needPressFile)) {
                return;
            }
            pressFile(out, needPressFile, parentDirName);

        }

    }

    private void pressFile(ZipOutputStream out, File file, String parentDirName) throws Exception {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            String path = parentDirName + Constant.SEPARATOR + file.getName();
            if (zipedPathDeal != null) {
                path = zipedPathDeal.deal(path);
            }
            out.putNextEntry(new ZipEntry(path));

            byte[] buff = new byte[cacheSize];
            int len = 0;

            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
                zipedLen += len;

                long nowTime = System.currentTimeMillis();
                if (nowTime - lastTime > 100) {
                    if (zipedLen < totalLen) {
                        onProgress(zipedLen);
                    }
                    lastTime = nowTime;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    LibLogs.e(e);
                }
            }
            input = null;
        }

    }

    private long getLength(File file) {
        long len = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < CountTool.count(files); i++) {
                len += getLength(files[i]);
            }
        } else {
            if (pass != null && pass.pass(file)) {
                return 0;
            }
            len = file.length();

        }
        return len;
    }

    private void onProgress(long currentLen) {
        if (this.onProgress != null) {
            this.onProgress.onProgress(totalLen, currentLen);
        }
    }

    private void onFinish(File file) {
        if (this.onFinish != null) {
            this.onFinish.onFinish(file);
        }
    }

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onError(throwable);
        }
        destroy();
    }
}
