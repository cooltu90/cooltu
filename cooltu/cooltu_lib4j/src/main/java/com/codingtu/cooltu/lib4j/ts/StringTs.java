package com.codingtu.cooltu.lib4j.ts;

import java.util.Objects;

public class StringTs extends CoreTs<String, StringTs> {

    private Ts.IsThisOne<String> getIsThisOne(String... symbols) {
        return new Ts.IsThisOne<String>() {
            @Override
            public boolean isThisOne(int position, String t) {
                for (int i = 0; i < symbols.length; i++) {
                    if (Objects.equals(symbols[i], t)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }


    public boolean has(String str) {
        return ts.contains(str);
    }

    public int index(String src) {
        return index(getIsThisOne(src));
    }

    public StringTs replace(String src, String target) {
        replace(target, getIsThisOne(src));
        return this;
    }


    public StringTs replaceOrAdd(String src, String target) {
        replaceOrAdd(target, getIsThisOne(src));
        return this;
    }


    public StringTs replaceAll(String src, String target) {
        replaceAll(target, getIsThisOne(src));
        return this;
    }


    public StringTs replaceAllOrAdd(String src, String target) {
        replaceAllOrAdd(target, getIsThisOne(src));
        return this;
    }


    public StringTs deleteOnce(String src) {
        deleteOnce(getIsThisOne(src));
        return this;
    }

    public StringTs delete(String... symbols) {
        delete(getIsThisOne(symbols));
        return this;
    }


}
