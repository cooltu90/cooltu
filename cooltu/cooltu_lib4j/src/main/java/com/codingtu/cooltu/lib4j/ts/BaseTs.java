package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.List;

public class BaseTs<T> extends CoreTs<T, BaseTs<T>> {

    public BaseTs() {
    }

    public BaseTs(List<T> list) {
        super(list);
    }

    public BaseTs<T> replace(List<T> srcs, Ts.IsThisOnePro<T> isThisOnePro) {
        return replace(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replace(Ts.IsThisOnePro<T> isThisOnePro, T... srcs) {
        return replace(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replace(BaseTs<T> srcTs, Ts.IsThisOnePro<T> isThisOnePro) {
        if (srcTs.isNull() || isThisOnePro == null)
            return this;
        srcTs.ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T src) {
                replace(src, new Ts.IsThisOne<T>() {
                    @Override
                    public boolean isThisOne(int position, T t) {
                        return isThisOnePro.isThisOne(position, src, t);
                    }
                });
                return false;
            }
        });
        return this;
    }


    public BaseTs<T> replaceAll(List<T> srcs, Ts.IsThisOnePro<T> isThisOnePro) {
        return replaceAll(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceAll(Ts.IsThisOnePro<T> isThisOnePro, T... srcs) {
        return replaceAll(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceAll(BaseTs<T> srcTs, Ts.IsThisOnePro<T> isThisOnePro) {
        if (srcTs.isNull() || isThisOnePro == null)
            return this;
        srcTs.ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T src) {
                replaceAll(src, new Ts.IsThisOne<T>() {
                    @Override
                    public boolean isThisOne(int position, T t) {
                        return isThisOnePro.isThisOne(position, src, t);
                    }
                });
                return false;
            }
        });
        return this;
    }

    public BaseTs<T> replaceOrAdd(List<T> srcs, Ts.IsThisOnePro<T> isThisOnePro) {
        return replaceOrAdd(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceOrAdd(Ts.IsThisOnePro<T> isThisOnePro, T... srcs) {
        return replaceOrAdd(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceOrAdd(BaseTs<T> srcTs, Ts.IsThisOnePro<T> isThisOnePro) {
        if (srcTs.isNull() || isThisOnePro == null)
            return this;
        srcTs.ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T src) {
                replaceOrAdd(src, new Ts.IsThisOne<T>() {
                    @Override
                    public boolean isThisOne(int position, T t) {
                        return isThisOnePro.isThisOne(position, src, t);
                    }
                });
                return false;
            }
        });
        return this;
    }


    public BaseTs<T> replaceAllOrAdd(List<T> srcs, Ts.IsThisOnePro<T> isThisOnePro) {
        return replaceAllOrAdd(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceAllOrAdd(Ts.IsThisOnePro<T> isThisOnePro, T... srcs) {
        return replaceAllOrAdd(Ts.ts(srcs), isThisOnePro);
    }

    public BaseTs<T> replaceAllOrAdd(BaseTs<T> srcTs, Ts.IsThisOnePro<T> isThisOnePro) {
        if (srcTs.isNull() || isThisOnePro == null)
            return this;
        srcTs.ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T src) {
                replaceAllOrAdd(src, new Ts.IsThisOne<T>() {
                    @Override
                    public boolean isThisOne(int position, T t) {
                        return isThisOnePro.isThisOne(position, src, t);
                    }
                });
                return false;
            }
        });
        return this;
    }

}
