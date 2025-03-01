package com.codingtu.cooltu.lib4j.vs;

public class StringVs extends CoreVs<String, StringVs> {
    @Override
    protected String valueSymbol(String s) {
        return s;
    }

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public String getFirstByValueSymbol(String valueId) {
        return super.getFirstByValueSymbol(valueId);
    }

    @Deprecated
    @Override
    public String getFirst(String s) {
        return super.getFirst(s);
    }

    @Deprecated
    @Override
    public String getFirst(Vs.IsThisOne<String> isThisOne) {
        return super.getFirst(isThisOne);
    }

    @Deprecated
    @Override
    public StringVs getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public StringVs getAll(String s) {
        return super.getAll(s);
    }

    @Deprecated
    @Override
    public StringVs getAll(Vs.IsThisOne<String> isThisOne) {
        return super.getAll(isThisOne);
    }

    /**************************************************
     * has
     **************************************************/
    @Deprecated
    @Override
    public boolean hasByValueSymbol(String valueId) {
        return super.hasByValueSymbol(valueId);
    }

    @Override
    public boolean has(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**************************************************
     * index
     **************************************************/
    @Deprecated
    @Override
    public int firstIndexByValueSymbol(String valueSymbol) {
        return firstIndex(valueSymbol);
    }

    @Override
    public int firstIndex(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstIndex(Vs.IsThisOne<String> isThisOne) {
        return super.firstIndex(isThisOne);
    }


    @Override
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return allIndex(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(String s) {
        int count = count();
        IntegerVs integerVs = new IntegerVs();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i).equals(s)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

}
