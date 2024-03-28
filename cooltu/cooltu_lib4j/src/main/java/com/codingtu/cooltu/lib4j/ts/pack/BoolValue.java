package com.codingtu.cooltu.lib4j.ts.pack;

public class BoolValue {

    public boolean value;

    public static BoolValue obtain() {
        return obtain(false);
    }

    public static BoolValue obtain(boolean value) {
        BoolValue boolValue = new BoolValue();
        boolValue.value = value;
        return boolValue;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
